var NoPreguntas = 2;
let CkAbierta = document.getElementById("RadioAbierta").checked;
let CkCerrada = document.getElementById("RadioCerrada").checked;
function PreguntaAbierta(NumeroAsk) {
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
        var e_eliminar = document.getElementById("Preguntas" + NumeroAsk);
        var throwawayNode = e.removeChild(e_eliminar);
        $("#RadioCerrada").prop('checked', false);
        $('#Pregunta').append("<div class='excerpt' id='Preguntas" + NoPreguntas + "'><p>Hola como estas?</p></div>");
        CkAbierta = prueba2;
        CkCerrada = false;
        NoPreguntas++;
    }

}

function PreguntaCerrada(NumeroAsk) {
    let prueba2 = document.getElementById("RadioCerrada").checked;
    console.log(NoPreguntas);
    if (CkCerrada == false && CkAbierta == false) {
        $('#latest').append("<div class='excerpt' id='Preguntas" + NoPreguntas + "'>     </div>");
        $("#RadioAbierta").prop('checked', false);
        CkCerrada = prueba2;
        CkAbierta = false;
        NoPreguntas++;
    }
    if (CkCerrada == false && CkAbierta == true) {
        NoPreguntas--;
        var e = document.getElementById("Pregunta");
        var e_eliminar = document.getElementById("Preguntas" + NumeroAsk);
        var throwawayNode = e.removeChild(e_eliminar);
        $("#RadioAbierta").prop('checked', false);
        $('#Pregunta').append("<div class='excerpt' id='Preguntas" + NoPreguntas + "'><p>Hola ya soy otro</p></div>");
        CkCerrada = prueba2;
        CkAbierta = false;
        NoPreguntas++;
    }
}


function Agregar(){
    
    var e = document.getElementById("btnAgregarMayor" + NoPreguntas);
    var e_eliminar = document.getElementById("btnAgregar" + NoPreguntas);
    var throwawayNode = e.removeChild(e_eliminar);
    NoPreguntas++;
    $('#Preguntass').append(actualizarplantilla);
}


function Quitar(){

}

function actualizarplantilla(){
var plantillapregunta = '<form name="Pregunta" id="Pregunta"> <ul id="latest" class="nospace group"> <li class="one_third first"> <article><a class="imgover" href="#"><img src="images/demo/348x261.png" alt=""></a> '+
'<ul class="nospace meta clear"> <li><i class="fas fa-user"></i> <a href="#">Pregunta</a></li> <li><i class="fas fa-comments"></i> <a href="#">Notas</a></li> </ul> <div class="excerpt"> '+
'<ul class="group"><div class="subgroup"><li><textarea name="Ponga Su Pregunta" id="Pregunta" cols="30"rows="2">Pregunta sin titulo</textarea></li></div><li class="subgroup2">'+
'<input type="radio" name="Abierta" id="RadioAbierta" onclick="PreguntaAbierta()"> Abierta</li><li class="subgroup3"><input type="radio" name="Cerrada" id="RadioCerrada" onclick="PreguntaCerrada()"> Cerrada'+
'</li></ul></div><div class="excerpt" id="Botones"><form id="Botoness"><ul class="group"><li><div id="btnAgregarMayor'+NoPreguntas+'"><button id="btnAgregar'+NoPreguntas +'"  type="button" onclick="Agregar()">Aceptar</button></div>'+
'</li><li><button type="button" onclick="Quitar()"> Quitar</button></li></ul></form></div></article></li></ul></form>';
return plantillapregunta
}