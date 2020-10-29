//url for local host and ec2
//const url = "http://52.87.233.199:8081/Project1/";
const url = "http://localhost:8080/Project1/";


console.log("inside login.js");

let params = new URLSearchParams(location.search);
if(params.get('fail') !=  null){
    document.getElementById("message").innerText = "Incorrect username/password";
} else if(params.get('logout') != null){
    document.getElementById("message").innerText = "Logged out";
}

today=new Date();
var cmas=new Date(today.getFullYear(), 11, 25);
if (today.getMonth()==11 && today.getDate()>25) 
{
cmas.setFullYear(cmas.getFullYear()+1); 
}  
var one_day=1000*60*60*24;
document.getElementById("d4").innerText = (Math.ceil((cmas.getTime()-today.getTime())/(one_day))+
" days left until Christmas!");
console.log('end of login');


async function register() {
    const user = {
        author: {
            userId: 0,
            username: document.getElementById("inusername").value,
            first: document.getElementById("infirst").value,
            last: document.getElementById("inlast").value,
            email: document.getElementById("inemail").value,
            role: {
                roleId: 2
            }
        },
    };
    const fetched = await fetch(url + 'add.json', {
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        },
        method: "POST",
        body: JSON.stringify(user),
    });
    const json = await fetched.json();
    console.log(json);
    const rows = document.getElementById('reimbTableBody').innerHTML = '';
    asyncFetch("http://localhost:8080/Project1/register.json", renderTable);
}