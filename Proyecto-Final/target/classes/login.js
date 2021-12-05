var Acceso = document.getElementById("Ingresar");
Acceso.addEventListener("click", function(){ 
    let usuario = document.getElementById("usuario");
    let password = document.getElementById("password");
    console.log(usu + pass);
    axios.get("http://localhost:1234/validar", {
        usuario: usuario,
        password: password
    })
    .then(function(rs) {
        console.log(rs.data);
    })
    .catch(function (error) {
        console.log(error);
    });
}, true);