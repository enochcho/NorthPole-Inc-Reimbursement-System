//url for local host and ec2
//const url = "http://52.87.233.199:8081/Project1/";
const url = "http://localhost:8080/Project1/";



/**
 * 
 * @param {*} reimbursements 
 * for rendering the view of the employees
 */
function renderTableE(reimbursements) {
    const tr = document.createElement("tr");
    const idTh = document.createElement("th");
    const typeTh = document.createElement("th");
    const amountTh = document.createElement("th");
    const descripTh = document.createElement("th");
    const submitTh = document.createElement("th");
    const statusTh = document.createElement("th");
    const resolveTh = document.createElement("th");
    const fileTh = document.createElement("th");
    const approvedTh = document.createElement("th");
    idTh.innerText = "ID";
    typeTh.innerText = "Type";
    amountTh.innerText = "Amount";
    descripTh.innerText = "Description";
    submitTh.innerText = "Time Submitted";
    statusTh.innerText = "Status";
    resolveTh.innerText = "Time Resolved";
    fileTh.innerText = "File";
    approvedTh.innerText = "Approved By";
    tr.append(idTh,typeTh,amountTh,descripTh,submitTh,statusTh,resolveTh,fileTh,approvedTh);
    document.getElementById("reimbTableHead").append(tr);
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
        idTd.innerText = reimb.id;
        typeTd.innerText = reimb.type.str;
        amountTd.innerText = "$" + reimb.amount.toFixed(2);
        descripTd.innerText = reimb.description;
        submitTd.innerText = new Date(reimb.submitTime);
        statusTd.innerText = reimb.status.str;
        if(reimb.resolveTime != null){
        resolveTd.innerText = new Date(reimb.resolveTime);
        } 
        fileTd.innerText = reimb.receipt;
        if(reimb.resolver != null){
            approvedTd.innerText = reimb.resolver.first + ' ' + reimb.resolver.last;
        }
        trr.append(idTd,typeTd,amountTd,descripTd,submitTd,statusTd,resolveTd,fileTd,approvedTd);
        document.getElementById("reimbTableBody").append(trr);
    }
}
/**
 * 
 * @param {*} reimbursements 
 * for rendering the view of the financial managers
 */
function renderTableF(reimbursements){
    const tr = document.createElement("tr");
    const idTh = document.createElement("th");
    const authorTh = document.createElement("th");
    const typeTh = document.createElement("th");
    const amountTh = document.createElement("th");
    const descripTh = document.createElement("th");
    const submitTh = document.createElement("th");
    const statusTh = document.createElement("th");
    const resolveTh = document.createElement("th");
    const fileTh = document.createElement("th");
    const approvedTh = document.createElement("th");
    idTh.innerText = "ID";
    authorTh.innerText = "Employee Name";
    typeTh.innerText = "Type";
    amountTh.innerText = "Amount";
    descripTh.innerText = "Description";
    submitTh.innerText = "Time Submitted";
    statusTh.innerText = "Status";
    resolveTh.innerText = "Time Resolved";
    fileTh.innerText = "File";
    approvedTh.innerText = "Approved By";
    tr.append(idTh,typeTh,amountTh,descripTh,submitTh,statusTh,resolveTh,fileTh,approvedTh);
    document.getElementById("reimbTableHead").append(tr);
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
        idTd.innerText = reimb.id;
        typeTd.innerText = reimb.type.str;
        amountTd.innerText = "$" + reimb.amount.toFixed(2);
        descripTd.innerText = reimb.description;
        submitTd.innerText = new Date(reimb.submitTime);
        statusTd.innerText = reimb.status.str;
        if(reimb.resolveTime != null){
        resolveTd.innerText = new Date(reimb.resolveTime);
        } 
        fileTd.innerText = reimb.receipt;
        if(reimb.resolver != null){
            approvedTd.innerText = reimb.resolver.first + ' ' + reimb.resolver.last;
        }
        trr.append(idTd,typeTd,amountTd,descripTd,submitTd,statusTd,resolveTd,fileTd,approvedTd);
        document.getElementById("reimbTableBody").append(trr);
    }

}



/**
 * 
 * @param {*} urll 
 * @param {*} expression 
 * This function performs a fetch with a call back function
 */
async function asyncFetch(urll, expression){
    try{
        const response = await fetch(urll);
        const json = await response.json();
        expression(json);
    } catch(e){
        console.log(e.message);
    }
}


asyncFetch(url+'all.json',renderTableE);


/**
 * Adding a reimbursement
 */
async function submitReimb(){
    //mock reimbursement
    const reimb = {
        id: 0,
        amount: 33,
        submitTime: null,
        resolveTime: null,
        description: "mocked insert",
        receipt: null,
        author: {
            userId:3,
            username:'santa',
            first: '',
            last: '',
            email: '',
            role:{
                typeId:2
            }
        },
        resolver: null,
        status: {
            statusId: 1
        },
        type: {
            typeId: 3
        }
    };
    const fetched = await fetch(url+'add.json', {
        headers: { 
        "Content-type": "application/json; charset=UTF-8"
   		 }, 
        method:"POST",
        body:JSON.stringify(reimb),
    });
    const json = await fetched.json();
    console.log(json);
    const rows = document.getElementById('reimbTableBody').innerHTML = '';
    const thead = document.getElementById('reimbTableHead').innerHTML='';
    asyncFetch("http://localhost:8080/Project1/all.json",renderTableE);
}

//approving/denying a reimbursement (random right now)
async function apprvDeny(){
    const jnode = {
        approverId: 1,
        reimbId: 4,
        approved: false
    };
    const fetched = await fetch(url+'aprvdeny.json', {
        headers: { 
        "Content-type": "application/json; charset=UTF-8"
   		 }, 
        method:"POST",
        body:JSON.stringify(jnode),
    });
    const json = await fetched.json();
    console.log(json);
    const rows = document.getElementById('reimbTableBody').innerHTML = '';
    const thead = document.getElementById('reimbTableHead').innerHTML='';
    asyncFetch("http://localhost:8080/Project1/all.json",renderTableE); //****change this later to F */
}


async function login(){
	const jnode = {
		username: 'santa',
		password: 'christmas'
	};
	const fetched = await fetch(url+'login.json', {
        headers: { 
        "Content-type": "application/json; charset=UTF-8"
   		 }, 
        method:"POST",
        body:JSON.stringify(jnode),
    });
	
	const json = await fetched.json();
	console.log(json);
}

document.getElementById('aprdny').addEventListener("click", apprvDeny);
document.getElementById('testSubmit').addEventListener("click",submitReimb);
document.getElementById('login').addEventListener("click", login);