//url for local host and ec2
const url = "http://52.87.233.199:8081/Project1/";
//const url = "http://localhost:8080/Project1/";

//Determines whether this is up-to-date. 
let current = false;

/**
 * 
 * @param {*} reimbursements 
 * for rendering the view of the employees
 */
function renderTable(reimbursements) {
    resetRadio();
    for (const reimb of reimbursements) {
        const trr = document.createElement("tr");
        const idTd = document.createElement("td");
        const typeTd = document.createElement("td")
        const amountTd = document.createElement("td")
        const descripTd = document.createElement("td")
        const submitTd = document.createElement("td")
        const statusTd = document.createElement("td")
        const resolveTd = document.createElement("td")
        const fileTd = document.createElement("td")
        const approvedTd = document.createElement("td")
        trr.classList = `${reimb.status.str} ${reimb.author.first}`;
        idTd.innerText = reimb.id;
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
        }
        trr.append(idTd, typeTd, amountTd, descripTd, submitTd, statusTd, resolveTd, fileTd, approvedTd);
        document.getElementById("reimbTableBody").append(trr);
    }
    current = true;
}


/**
 * Adding a reimbursement
 */
async function submitReimb() {
    //mock reimbursement
    const reimb = {
        id: 0,
        amount: document.getElementById("inamount").value,
        submitTime: null,
        resolveTime: null,
        description: document.getElementById("indescription").value,
        receipt: null,
        author: {
            userId: 0,
            username: '',
            first: '',
            last: '',
            email: '',
            role: {
                roleId: 2
            }
        },
        resolver: null,
        status: {
            statusId: 1
        },
        type: {
            typeId: document.getElementById("intype").value
        }
    };
    const fetched = await fetch(url + 'add.json', {
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        },
        method: "POST",
        body: JSON.stringify(reimb),
    });
    const json = await fetched.json();
    document.getElementById("message").innerText = json.message;
    const rows = document.getElementById('reimbTableBody').innerHTML = '';
    asyncFetch("http://localhost:8080/Project1/empl.json", renderTable);
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
        expression(json);
    } catch (e) {
        console.log(e.message);
    }
}


/**Employee methods:
 * look at reimbursements by 
 *  - pending
 *  - approved
 *  - denied
 * 
 * Submit Requests
 * 
 * log out
 */
asyncFetch(url + 'empl.json', renderTable); // this loads the page automatically ** fix for this employee
document.getElementById('submit').addEventListener("click",submitReimb);
//adding radio buttons event listeners
let radios = document.querySelectorAll(".statusbuttons");
for (lab of radios) {
    lab.addEventListener("click", toggleStatus);
}

let status = "All";

async function toggleView() {
    console.log("inside toggle view");
    let x = document.querySelectorAll('#reimbTableBody tr');

    if (status == 'All') {
        for (y of x) {
            y.style.display = 'table-row';
        }
    } else {
        for (y of x) {
            if (y.classList.contains(status)) {
                y.style.display = 'table-row';
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