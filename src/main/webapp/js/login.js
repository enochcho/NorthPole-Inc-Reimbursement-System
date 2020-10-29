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
