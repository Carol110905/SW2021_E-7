var NoPreguntas = 1;
let CkAbierta = document.getElementById("RadioAbierta").checked;
let CkCerrada = document.getElementById("RadioCerrada").checked;
function PreguntaAbierta() {
    let prueba2 = document.getElementById("RadioAbierta").checked;
    console.log(NoPreguntas);
    if (CkAbierta == false && CkCerrada == false) {
        $('#Pregunta').append("<div class='excerpt' id='Preguntas" + NoPreguntas + "'><p>Hola como estas?</p></div>");
        $("#RadioCerrada").prop('checked', false);
        CkAbierta = prueba2;
        CkCerrada = false;
        NoPreguntas++;
    }
    if (CkAbierta == false && CkCerrada == true) {
        NoPreguntas--;
        var e = document.getElementById("Pregunta");
        var e_eliminar = document.getElementById("Preguntas" + NoPreguntas);
        var throwawayNode = e.removeChild(e_eliminar);
        $("#RadioCerrada").prop('checked', false);
        $('#Pregunta').append("<div class='excerpt' id='Preguntas" + NoPreguntas + "'><p>Hola como estas?</p></div>");
        CkAbierta = prueba2;
        CkCerrada = false;
        NoPreguntas++;
    }

}

function PreguntaCerrada() {
    let prueba2 = document.getElementById("RadioCerrada").checked;
    console.log(NoPreguntas);
    if (CkCerrada == false && CkAbierta == false) {
        $('#Pregunta').append("<div class='excerpt' id='Preguntas" + NoPreguntas + "'><p>Hola ya soy otro</p></div>");
        $("#RadioAbierta").prop('checked', false);
        CkCerrada = prueba2;
        CkAbierta = false;
        NoPreguntas++;
    }
    if (CkCerrada == false && CkAbierta == true) {
        NoPreguntas--;
        var e = document.getElementById("Pregunta");
        var e_eliminar = document.getElementById("Preguntas" + NoPreguntas);
        var throwawayNode = e.removeChild(e_eliminar);
        $("#RadioAbierta").prop('checked', false);
        $('#Pregunta').append("<div class='excerpt' id='Preguntas" + NoPreguntas + "'><p>Hola ya soy otro</p></div>");
        CkCerrada = prueba2;
        CkAbierta = false;
        NoPreguntas++;
    }
}
