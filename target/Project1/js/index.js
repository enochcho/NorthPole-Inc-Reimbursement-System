// render table for an employee
// function renderTableE(reimbursements) {
//     let tr = document.createElement(tr);
//     const idTd = document.createElement(td);
//     const typeTd = document.createElement(td)
//     const amountTd = document.createElement(td)
//     const descripTd = document.createElement(td)
//     const submitTd = document.createElement(td)
//     const statusTd = document.createElement(td)
//     const resolveTd = document.createElement(td)
//     const fileTd = document.createElement(td)
//     const approvedTd = document.createElement(td)
//     idTd.innerText = "Reimbursement ID";
//     typeTd.innerText = "Type";
//     amountTd.innerText = "Amount";
//     descripTd.innerText = "Description";
//     submitTd.innerText = "Time Submitted";
//     statusTd.innerText = "Status";
//     resolveTd.innerText = "Time Resolved";
//     fileTd.innerText = "File";
//     approvedTd.innerText = "Approved By";
//     tr.append(idTd,typeTd,amountTd,descripTd,submitTd,statusTd,resolveTd,fileTd,approvedTd);
//     document.getElementById("reimbTableHead").append(tr);
//     for (const reimb of reimbursements) {
//         tr = document.createElement(tr);
//         idTd.innerText = reimb.id;
//         typeTd.innerText = reimb.status.

//     }
// }

async function asyncFetch(url){
    try{
        const response = await fetch(url);
        const json = await response.json();
        console.log(json);
    } catch(e){
        console.log(e.message);
    }
}

//asyncFetch("http://52.87.233.199:8081/Project1/all.json");
asyncFetch("http://localhost:8080/Project1/all.json");
