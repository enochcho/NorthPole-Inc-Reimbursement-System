

//url for local host and ec2
const url = "http://52.87.233.199:8081/Project1/";
//const url = "http://localhost:8080/Project1/";


/**
 * 
 * @param {*} reimbursements 
 * for rendering the view of the financial managers  **neeeed to fix this
 */
function renderTable(reimbursements) {
    selEmpl = "All";
    resetRadio();
    //table
    
    //creates map for dropdown
    const names = new Map();
    for (const reimb of reimbursements) {
        //dropdown
        names.set(`${reimb.author.first}`, `${reimb.author.last}`);
        //table
        const trr = document.createElement("tr");
        const idTd = document.createElement("td");
        const authorTd = document.createElement("td");
        const typeTd = document.createElement("td")
        const amountTd = document.createElement("td")
        const descripTd = document.createElement("td")
        const submitTd = document.createElement("td")
        const statusTd = document.createElement("td")
        const approvedTd = document.createElement("td")
        const resolveTd = document.createElement("td")
        const fileTd = document.createElement("td")
        trr.classList = `${reimb.status.str} ${reimb.author.first}`;
        idTd.innerText = reimb.id;
        authorTd.innerText = reimb.author.first + ' ' + reimb.author.last;
        typeTd.innerText = reimb.type.str;
        amountTd.innerText = "$" + reimb.amount.toFixed(2);
        descripTd.innerText = reimb.description;
        let a = new Date(reimb.submitTime);
        submitTd.innerText = a.format("ddd mmm dd yyyy h:MM TT");
        statusTd.innerText = reimb.status.str;
        if (reimb.resolveTime != null) {
            let b = new Date(reimb.resolveTime);
            resolveTd.innerText = b.format("ddd mmm dd yyyy h:MM TT");
        }
        fileTd.innerText = reimb.receipt;
        if (reimb.resolver != null) {
            approvedTd.innerText = reimb.resolver.first + ' ' + reimb.resolver.last;
        } else {
            //adding approve or deny buttons
            const approvebutton = document.createElement("button");
            approvebutton.innerText = "Approve";
            approvebutton.classList = "btn btn-success btn-sm btn-block";
            approvebutton.type = 'button';
            approvebutton.name = reimb.id;
            approvebutton.addEventListener("click", apprvDeny);
            const denybutton = document.createElement("button");
            denybutton.innerText = "Deny";
            denybutton.classList = "btn btn-danger btn-sm btn-block";
            denybutton.type = 'button';
            denybutton.name = reimb.id;
            denybutton.addEventListener("click", apprvDeny);
            approvedTd.append(approvebutton, denybutton);
        }
        trr.append(idTd, authorTd, typeTd, amountTd, descripTd, submitTd, statusTd, approvedTd, resolveTd, fileTd);
        document.getElementById("reimbTableBody").append(trr);
    }
    //dropdown
    const empDrop = document.getElementById("empDrop");
    const button = document.createElement("button");
    button.type = 'button';
    button.name = `All`;
    button.classList = `dropdown-item`;
    button.innerText = "Show All Employees";
    empDrop.append(button);
    for (let [key, value] of names.entries()) {
        const button = document.createElement("button");
        button.type = 'button';
        button.name = `${key}`;
        button.classList = `dropdown-item`;
        button.innerText = `${key} ${value}`;
        empDrop.append(button);
    }
    let drops = document.querySelectorAll('button.dropdown-item');
    console.log(drops);
    for (let dro of drops) {
        dro.addEventListener("click", toggleEmpl);
        console.log("its adding");
    }


}






//approving/denying a reimbursement 
async function apprvDeny(ace) {

    let approve = true;
    if (ace.target.innerText == "Deny") {
        approve = false;
    }

    const jnode = {
        reimbId: ace.target.name,
        approved: approve
    };
    const fetched = await fetch(url + 'aprvdeny.json', {
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        },
        method: "POST",
        body: JSON.stringify(jnode),
    });
    const json = await fetched.json();
    console.log(json);
    const rows = document.getElementById('reimbTableBody').innerHTML = '';
    const drops = document.getElementById('empDrop').innerHTML = '';
    asyncFetch(url + "all.json", renderTable);
}


/**
 * 
 * @param {*} urll 
 * @param {*} expression 
 * This function performs a fetch with a call back function
 */
async function asyncFetch(urll, expression) {
    try {
        const response = await fetch(urll);
        const json = await response.json();
        console.log(json);
        expression(json);
    } catch (e) {
        console.log(e.message);
    }
}

/**
 * Financial manager methods:
 * get all reimbursements
 * look at reimbursements by:
 *  - employee
 * look at reimbursements by 
 *  - pending
 *  - approved
 *  - denied
 * log out
 * 
 */
asyncFetch(url + 'all.json', renderTable);

//adding radio buttons event listeners
let radios = document.querySelectorAll(".statusbuttons");
for (lab of radios) {
    lab.addEventListener("click", toggleStatus);
}

//first name of employee currently being shown
let selEmpl = "All";
let status = "All";

async function toggleView() {
    console.log("inside toggle view");
    let x = document.querySelectorAll('#reimbTableBody tr');

    if (status == 'All') {
        for (y of x) {
            if (selEmpl == "All" || y.classList.contains(selEmpl)) {
                y.style.display = 'table-row';
            } else {
                y.style.display = 'none';
            }
        }
    } else {
        for (y of x) {
            if (y.classList.contains(status)) {
                if (selEmpl == "All" || y.classList.contains(selEmpl)) {
                    y.style.display = 'table-row';
                } else {
                    y.style.display = 'none';
                }
            } else {
                y.style.display = 'none';
            }
        }
    }
}

async function toggleStatus(ace) {
    console.log(ace.target);
    status = ace.target.name;
    toggleView();
}

async function toggleEmpl(ace) {
    console.log(ace.target);
    selEmpl = ace.target.name;

    document.getElementById("dropdownMenuButton").innerText = ace.target.innerText;
    resetRadio().then(toggleView);
}

async function resetRadio() {
    status = "All";
    let labels = document.getElementsByTagName("label");
    let changed = false;
    for (let labs of labels) {
        if (!changed) {
            labs.classList = "btn btn-secondary active";
            changed = true;
        } else {
            labs.classList = "btn btn-secondary";
        }
    }
}