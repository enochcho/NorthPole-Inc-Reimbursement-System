//url for local host and ec2
//const url = "http://52.87.233.199:8081/Project1/";
const url = "http://localhost:8080/Project1/";


console.log("inside login.js");


let params = new URLSearchParams(location.search);
if (params.get('fail') != null) {
    document.getElementById("message").innerText = "Incorrect username/password";
} else if (params.get('logout') != null) {
    document.getElementById("message").innerText = "Logged out";
}

let today = new Date();
var cmas = new Date(today.getFullYear(), 11, 25);
if (today.getMonth() == 11 && today.getDate() > 25) {
    cmas.setFullYear(cmas.getFullYear() + 1);
}
var one_day = 1000 * 60 * 60 * 24;
document.getElementById("d4").innerText = (Math.ceil((cmas.getTime() - today.getTime()) / (one_day)) +
    " days left until Christmas!");



async function register() {
    const jnode = {
        password : document.getElementById("inpassword").value,
        userId: 0,
        username: document.getElementById("inusername").value,
        first: document.getElementById("infirst").value,
        last: document.getElementById("inlast").value,
        email: document.getElementById("inemail").value,
        roleId: 2

    };
    const fetched = await fetch(url + 'register.json', {
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        },
        method: "POST",
        body: JSON.stringify(jnode),
    });
    const json = await fetched.json();
    document.getElementById("msg").innerText= json.message;
}
document.getElementById('submit').addEventListener('click', register)