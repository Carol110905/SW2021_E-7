function enviarMateria(Examen, materia) {
    var parametros;
    var url;
    parametros.append('materia', materia)
    url = "http://localhost:1234/crearExamen" + parametros;
    alert(url);
    /*if (Examenes == "false") {
        axios.get(, parametros)
            .then(function (rs) {
                console.log(rs.data);
            })
            .catch(function (error) {
                console.log(error);
            });
    }*/
};

