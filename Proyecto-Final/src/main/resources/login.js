var Acceso = document.getElementById("Ingresar");
Acceso.addEventListener("click", function(){ 
    var params = new URLSearchParams();;
    var usu = document.getElementById("usuario");
    var pass = document.getElementById("password");
    params.append("usuario", usu);
    params.append("password", pass);
    console.log(usu + pass);
    axios.get("http://localhost:4567/validar", {
        usuario: params.get("usuario"), password: params.get("password")
    })
    .then(function(rs) {
        console.log(rs.data);
    })
    .catch(function (error) {
        console.log(error);
    });
}, true);