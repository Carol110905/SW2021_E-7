var NoPreguntas = 1;

function PreguntaAbierta(num) {
    let CkAbierta = document.getElementById("RadioAbierta" + num).checked;
    let CkCerrada = document.getElementById("RadioCerrada" + num).checked;
    let existe = document.getElementById("Respuestas" + (num));
    if (existe != null) {
        if (CkAbierta == true && CkCerrada == true) {
            NoPreguntas--;
            var e = document.getElementById("Pregunta"+num);
            var e_eliminar = document.getElementById("Respuestas" + num);
            var throwawayNode = e.removeChild(e_eliminar);
            $("#RadioCerrada" + num).prop('checked', false);
            $('#Pregunta'+num).append(AgregarAbierta(num));/* "<div class='excerpt' id='Preguntas" + num + "'><p>Hola como estas?</p></div>" */
            CkAbierta = true;
            CkCerrada = false;
            NoPreguntas++;
        }
        return;
    }
    if (CkAbierta == true && CkCerrada == false) {
        $('#Pregunta'+num).append(AgregarAbierta(num));
        $("#RadioCerrada" + num).prop('checked', false);
        CkAbierta = true;
        CkCerrada = false;
        NoPreguntas++;
    }


}

function PreguntaCerrada(num) {
    let CkAbierta = document.getElementById("RadioAbierta" + num).checked;
    let CkCerrada = document.getElementById("RadioCerrada" + num).checked;
    let existe = document.getElementById("Respuestas" + (num));
    if (existe != null) {
        if (CkCerrada == true && CkAbierta == true) {
            NoPreguntas--;
            var e = document.getElementById("Pregunta"+num);
            var e_eliminar = document.getElementById("Respuestas" + num);
            var throwawayNode = e.removeChild(e_eliminar);
            $("#RadioAbierta" + num).prop('checked', false);
            $('#Pregunta'+num).append(AgregarCerradas(num));
            CkCerrada = true;
            CkAbierta = false;
            NoPreguntas++;
        }
        return;
    }
    if (CkCerrada == true && CkAbierta == false) {
        $('#Pregunta'+num).append(AgregarCerradas(num));
        $("#RadioAbierta" + num).prop('checked', false);
        CkCerrada = true;
        CkAbierta = false;
        NoPreguntas++;
    }

}


function Agregar(num) {
    let existe = document.getElementById("Preguntas" + (num));
    if (existe != null) {
        var e = document.getElementById("btnAgregarMayor" + (num));
        var e_eliminar = document.getElementById("btnAgregar" + (num));
        var throwawayNode = e.removeChild(e_eliminar);
        $('#Preguntass').append(actualizarplantilla);
    }else{
        var e = document.getElementById("btnAgregarMayor" + (num));
        var e_eliminar = document.getElementById("btnAgregar" + (num));
        var throwawayNode = e.removeChild(e_eliminar);
        NoPreguntas++;
        $('#Preguntass').append(actualizarplantilla);
    }
}


function Quitar(num) {
    if(num != null){
        var e = document.getElementById("Preguntass");
        var e_eliminar = document.getElementById("Pregunta" + (num));
        var throwawayNode = e.removeChild(e_eliminar);
    }
}

function actualizarplantilla() {
    var plantillapregunta = '<form name="Pregunta' + NoPreguntas + '" id="Pregunta' + NoPreguntas + '"> <ul id="latest" class="nospace group"> <li class="one_third first"> <article><a class="imgover" href="#"><img src="images/demo/348x261.png" alt=""></a> ' +
        '<ul class="nospace meta clear"> <li><i class="fas fa-user"></i> <a href="#">Pregunta</a></li> <li><i class="fas fa-comments"></i> <a href="#">Notas</a></li> </ul> <div class="excerpt"> ' +
        '<ul class="group"><div class="subgroup"><li><textarea name="Ponga Su Pregunta" id="Pregunta" cols="30"rows="2">Pregunta sin titulo</textarea></li></div><li class="subgroup2">' +
        '<input type="radio" name="Abierta" id="RadioAbierta'+NoPreguntas+'" onclick="PreguntaAbierta('+NoPreguntas+')"> Abierta</li><li class="subgroup3"><input type="radio" name="Cerrada" id="RadioCerrada'+NoPreguntas+'" onclick="PreguntaCerrada('+NoPreguntas+')"> Cerrada' +
        '</li></ul></div><div class="excerpt" id="Botones"><form id="Botoness"><ul class="group"><li><div id="btnAgregarMayor' + NoPreguntas + '"><button id="btnAgregar' + NoPreguntas + '"  type="button" onclick="Agregar('+NoPreguntas+')">Aceptar</button></div>' +
        '</li><li><button type="button" onclick="Quitar('+NoPreguntas+')"> Quitar</button></li></ul></form></div></article></li></ul></form>';
    return plantillapregunta
}

function AgregarCerradas(num){
    var preguntasCerradas = '<form id="Respuestas'+num+'" action=""><div class="txtRespuestas"><article><ul id="latest" class="nospace group"><li class="one_third first">'+
    '<textarea name="Ponga Su Pregunta" id="Pregunta1('+num+')" cols="30" rows="2">Respuesta 1</textarea> </li><li class="one_third first"><textarea name="Ponga Su Pregunta" id="Pregunta2('+num+')" cols="30" rows="2">Respuesta 2</textarea>'+
    '</li><li class="one_third first"><textarea name="Ponga Su Pregunta" id="Pregunta3('+num+')" cols="30" rows="2">Respuesta 3</textarea></li><li class="one_third first">'+
    '<textarea name="Ponga Su Pregunta" id="Pregunta4('+num+')" cols="30" rows="2">Respuesta 4</textarea></li></ul></article></div></form> ';
    return preguntasCerradas
}

function AgregarAbierta(num){
    var preguntaAbierta = '<form id="Respuestas'+num+'" action=""><div class="txtRespuestas"><article><ul id="latest" class="nospace group">'+
    '<li class="one_third first"><p>Video Futuro</p></li><li class="one_third first">'+
    '<textarea name="Ponga Su Pregunta" id="Pregunta('+num+')" cols="30" rows="2">Respuesta</textarea></li></ul></article></div></form>';
    return preguntaAbierta
}