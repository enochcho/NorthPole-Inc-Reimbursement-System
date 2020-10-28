//url for local host and ec2
const url = "http://52.87.233.199:8081/Project1/all.json";
//const url = "http://localhost:8080/Project1/all.json";

//render table for an employee
function renderTableE(reimbursements) {
	console.log("hello1");
    const tr = document.createElement("tr");
    console.log("hello2");
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

async function asyncFetch(url, expression){
    try{
        const response = await fetch(url);
        const json = await response.json();
        console.log(json);
        expression(json);
    } catch(e){
        console.log(e.message);
    }
}


asyncFetch(url,renderTableE);


//test adding a reimbursement
async function submitReimb(){
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
    const fetched = await fetch(url, {
        headers: { 
        "Content-type": "application/json; charset=UTF-8"
   		 }, 
        method:"POST",
        body:JSON.stringify(reimb),
    });
    const json = await fetched;
    const rows = document.getElementById('reimbTableBody').innerHTML = '';
    const thead = document.getElementById('reimbTableHead').innerHTML='';
    asyncFetch("http://localhost:8080/Project1/all.json",renderTableE);
}


document.getElementById('testSubmit').addEventListener("click",submitReimb);