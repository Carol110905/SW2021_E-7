var NoPreguntas = 1;
function PreguntaAbierta(num){
    let CkAbierta = document.getElementById("RadioAbierta" + num).checked;
    let CkCerrada = document.getElementById("RadioCerrada" + num).checked;
    let existe = document.getElementById("ElemntosPreguntas" + (num));
    alert(CkAbierta + ' ' +CkCerrada + ' ' + existe);
    if(existe != null){
        if (CkAbierta == true && CkCerrada == true) {

            var e = document.getElementById("TiposPreguntas"+num);
            var e_eliminar = document.getElementById("ElemntosPreguntas" + num);
            var throwawayNode = e.removeChild(e_eliminar);
            $('#RadioCerrada' + num).prop('checked', false);
            $('#TiposPreguntas'+num).append(AgregarAbierta(num));/* "<div class='excerpt' id='Preguntas" + num + "'><p>Hola como estas?</p></div>" */
            CkAbierta = true;
            CkCerrada = false;
        }
        return;
    }
    if (CkAbierta == true && CkCerrada == false) {
        $('#TiposPreguntas'+num).append(AgregarAbierta(num));
        $('#RadioCerrada' + num).prop('checked', false);
        CkAbierta = true;
        CkCerrada = false;
    }
    
}


function PreguntaCerrada(num) {
    let CkAbierta = document.getElementById("RadioAbierta" + num).checked;
    let CkCerrada = document.getElementById("RadioCerrada" + num).checked;
    let existe = document.getElementById("ElemntosPreguntas" + (num));
    if (existe != null) {
        if (CkCerrada == true && CkAbierta == true) {
            var e = document.getElementById("TiposPreguntas"+num);
            var e_eliminar = document.getElementById("ElemntosPreguntas" + num);
            var throwawayNode = e.removeChild(e_eliminar);
            $('#RadioAbierta' + num).prop('checked', false);
            $('#TiposPreguntas'+num).append(AgregarCerradas(num));
            CkCerrada = true;
            CkAbierta = false;
        }
        return;
    }
    if (CkCerrada == true && CkAbierta == false) {
        $('#TiposPreguntas'+num).append(AgregarCerradas(num));
        $('#RadioAbierta' + num).prop('checked', false);
        CkCerrada = true;
        CkAbierta = false;
    }

}


function Agregar(num) {
    $("#Preguntas").append(actualizarplantilla);
    var e_eliminar = document.getElementById("btnAgregar" + (num));
    e_eliminar.parentNode.removeChild(e_eliminar);
}


function Quitar(num) {
    if(num != null){
        var e_eliminar = document.getElementById(num);
        e_eliminar.parentNode.removeChild(e_eliminar);
    }
}

function actualizarplantilla() {
    var plantillapregunta = '<div id="'+NoPreguntas+'" class="media tm-media"><div class="media-body tm-box-5"><h2>Etiam tincidunt ullamcorper</h2><textarea name="Ponga Su Pregunta" id="txtPregunta'+NoPreguntas+'" cols="30" rows="2"'+
    'placeholder="Pregunta sin titulo"></textarea><ul><li><input type="radio" name="Abierta" id="RadioAbierta'+NoPreguntas+'" onclick="PreguntaAbierta('+NoPreguntas+')">Abierta</li>'+
    '<li><input type="radio" name="Cerrada" id="RadioCerrada'+NoPreguntas+'" onclick="PreguntaCerrada('+NoPreguntas+')">Cerrada</li></ul><div id="TiposPreguntas'+NoPreguntas+'">'+
    '</div><div id="BotonesPreguntas'+NoPreguntas+'" class="media-body tm-box-5-1"><ul><li><button class="btn" id="btnAgregar'+NoPreguntas+'" type="button" onclick="Agregar('+NoPreguntas+')">Aceptar</button>'+
    '</li><li><button class="btn" type="button" onclick="Quitar('+NoPreguntas+')">Quitar</button></li></ul></div></div></div>';
    NoPreguntas++;
    return plantillapregunta;
}

function AgregarCerradas(num){
    var preguntasCerradas = '<div id="ElemntosPreguntas'+num+'" class="ElementosPreguntasCerradas"><ul><li><h3>Respuesta Correcta:</h3></li><li><textarea name="Ponga Su Pregunta" id="Pregunta" cols="30" rows="2" '+
    'placeholder="Ponga la Respuesta"></textarea></li></ul><ul><li><h3>Opcion 1:</h3></li><li><textarea name="Ponga Su Pregunta" id="Pregunta1('+num+')" cols="30" rows="2"'+
    'placeholder="Ponga la Respuesta"></textarea></li></ul><ul><li><input type="file" name=Fpregunta1('+num+') id="Fpregunta1('+num+')"> </li></ul><ul><li><h3>Opcion 2:</h3>'+
    '</li><li><textarea name="Ponga Su Pregunta" id="Pregunta2('+num+')" cols="30" rows="2"placeholder="Ponga la Respuesta"></textarea></li></ul><ul><li><input type="file" name=Fpregunta2('+num+') id="Fpregunta2('+num+')"> '+
    '</ul><ul><li><h3>Opcion 3:</h3></li><li><textarea name="Ponga Su Pregunta" id="Pregunta('+num+')" cols="30" rows="2"placeholder="Ponga la Respuesta"></textarea>'+
    '</li></ul><ul><li><input type="file" name=Fpregunta3('+num+') id="Fpregunta3('+num+')"> </li></ul><ul><li><h3>Opcion 4:</h3></li><li><textarea name="Ponga Su Pregunta" id="Pregunta('+num+')" cols="30" rows="2"'+
    'placeholder="Ponga la Respuesta"></textarea></li></ul><ul><li> <input type="file" name=Fpregunta4('+num+') id="Fpregunta4('+num+')"> </li></ul></div>';
    return preguntasCerradas;
}

function AgregarAbierta(num){
    var preguntaAbierta = '<div id="ElemntosPreguntas'+num+'" class="ElementosPreguntasAbiertas"> <ul> <li> <h3>Respuesta:</h3>'+
    '</li><li><textarea name="Ponga Su Pregunta" id="Pregunta" cols="30" rows="2"placeholder="Ponga la Respuesta"></textarea></li>'+
    '</ul></div>'
    return preguntaAbierta;
}