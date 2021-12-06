function enviarMateria(Examen, materia) {
    let Examenes = Examen;
    let materias = materia;
    alert(materias);
    alert(materia)
    console.log(Examenes)
    if (Examenes == "false") {
        axios.get("http://localhost:1234/crearExamen", {
            materias: materia,
        })

        .then(function (rs) {
            console.log(rs.data);
        })
        .catch(function (error) {
            console.log(error);
        });
    }
};
