const url = 'http://localhost:8080/api/users';
const usersTable = document.getElementById("usersTable");
const editId = document.getElementById("editId");
const editFirstName = document.getElementById("editFirstName");
const editLastName = document.getElementById("editLastName");
const editEmail = document.getElementById("editEmail");
const editPassword = document.getElementById("editPassword");
const submitEditBtn = document.querySelector('.submitEditBtn');
const editRoles = document.getElementById("editRoles");
const deleteId = document.getElementById("deleteId");
const deleteFirstName = document.getElementById("deleteFirstName");
const deleteLastName = document.getElementById("deleteLastName");
const deleteEmail = document.getElementById("deleteEmail");
const deletePassword = document.getElementById("deletePassword");
const submitDeleteBtn = document.querySelector('.submitDeleteBtn');
const addNewUserForm = document.querySelector('.addNewUser');
const newUserFirstName = document.getElementById("first-name");
const newUserLastName = document.getElementById("last-name");
const newUserEmail = document.getElementById("email");
const newUserPassword = document.getElementById("password");
const newUserRoles = document.getElementById("roles");
const navBarAuthUserInfo = document.getElementById("authUserInfo");

fetch('http://localhost:8080/api/authUser').then(res => res.json())
    .then(user => {
        let temp = "";

        temp += `<span class=\"text-white\"><b>${user.email}</b></span>
                    <span class=\"text-white\">with roles:</span>
                    <span class=\"text-white\">`;
        for (let i = 0, l = user.roles.length; i < l; i++) {roles
            temp += user.roles[i].role + " ";
        }
        temp += "</span>";

        navBarAuthUserInfo.innerHTML = temp;
    })

fetch(url).then(res => res.json())
    .then(data => getUsersTable(data));

function getUsersTable(data) {
    if (data.length > 0){
        let temp = "";
        data.forEach((user)=>{
            temp += `<tr>
                     <td hidden=\"hidden\" class=\"id\">${user.id}</td>
                     <td class=\"firstName\">${user.name}</td>
                     <td class=\"lastName\">${user.lastName}</td>
                     <td class=\"email\">${user.email}</td>
                     <td hidden=\"hidden\" class=\"password\">${user.pass}</td>`;
            temp += "<td class=\"roles\">";
            for (let i = 0, l = user.roles.length; i < l; i++) {
                temp += user.roles[i].role + " ";
            }
            temp += "</td>";
            temp += `<td data-id=${user.id}>
                     <button class=\"btn btn-info text-white\" id=\"editBtn\">Edit</button>
                     </td>`;
            temp += "<td>" + "<a class=\"btn btn-danger text-white\" id=\"deleteBtn\">Delete</a>" +
                "</td>";
            temp += "</tr>";
        })
        usersTable.innerHTML = temp;
    }
}

function getSelectedRoles(select) {
    let roles = [];

    for (let i = 0, len = select.selectedOptions.length; i < len; i++) {

        if (select.selectedOptions[i].value == "1") {
            let role = {"id":1,"role":"ROLE_USER"};
            roles.push(role);
            ;
        }

        if (select.selectedOptions[i].value == "2") {
            let role = {"id":2,"role":"ROLE_ADMIN"};
            roles.push(role);
        }
    }
    return roles;
}

usersTable.addEventListener('click', (e) => {
    e.preventDefault();
    let deleteBtnIsPressed = e.target.id == 'deleteBtn';
    let editBtnIsPressed = e.target.id == 'editBtn';

    const parent = e.target.parentElement.parentElement;

    let userId = parent.querySelector('.id').textContent;
    let firstName = parent.querySelector('.firstName').textContent;
    let lastName = parent.querySelector('.lastName').textContent;
    let email = parent.querySelector('.email').textContent;
    let password = parent.querySelector('.password').textContent;

    if(deleteBtnIsPressed) {

        deleteId.value = userId;
        deleteFirstName.value = firstName;
        deleteLastName.value = lastName;
        deleteEmail.value = email;
        deletePassword.value = password;

        $('#deleteModal').modal({show: true});
    }
    submitDeleteBtn.addEventListener('click', () => {
        fetch(`${url}/${userId}`, {method: 'DELETE'})
            .then(res => res.json())
            .then(data => getUsersTable(data))
    })

    if(editBtnIsPressed) {

        editId.value = userId;
        editFirstName.value = firstName;
        editLastName.value = lastName;
        editEmail.value = email;
        editPassword.value = password;

        $('#editModal').modal({ show: true });
    }
    submitEditBtn.addEventListener('click', () => {
        fetch(url, {method: 'PUT',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                id: editId.value,
                name: editFirstName.value,
                lastName: editLastName.value,
                email: editEmail.value,
                pass: editPassword.value,
                roles: getSelectedRoles(editRoles)
            })
        })
            .then(res => res.json())
            .then(data => getUsersTable(data))
    })
});

addNewUserForm.addEventListener('submit', (e) => {
    e.preventDefault();
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            name: newUserFirstName.value,
            lastName: newUserLastName.value,
            email: newUserEmail.value,
            pass: newUserPassword.value,
            roles: getSelectedRoles(newUserRoles)
        })
    })
        .then(res => res.json())
        .then(data => getUsersTable(data))
})

