const navBarAuthUserInfo = document.getElementById("authUserInfo");
const authUserTable = document.getElementById("authUserTable");


fetch('http://localhost:8080/api/authUser').then(res => res.json())
    .then(user => {
        let temp = "";
        temp += `<span class=\"text-white\"><b>${user.email}</b></span>
                    <span class=\"text-white\">with roles:</span>
                    <span class=\"text-white\">`;
        for (let i = 0, l = user.roles.length; i < l; i++) {
            temp += user.roles[i].role + " ";
        }
        temp += "</span>";

        navBarAuthUserInfo.innerHTML = temp;
    })

fetch('http://localhost:8080/api/authUser').then(res => res.json())
    .then(user => {
        let temp = "";
        temp += `<td>${user.name}</td>
                     <td>${user.lastName}</td>
                     <td>${user.email}</td>
                     <td>`;
        for (let i = 0, l = user.roles.length; i < l; i++) {
            temp += user.roles[i].role + " ";
        }
        temp += "</td>";
        authUserTable.innerHTML = temp;
    })