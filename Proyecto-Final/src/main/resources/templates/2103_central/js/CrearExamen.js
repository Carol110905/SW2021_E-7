var NoPreguntas = 1;
let ArrayAbiertas = new Array();
let ArrayCerrada = new Array();


function PreguntaAbierta(num) {
    let CkAbierta = document.getElementById("RadioAbierta" + num).checked;
    let CkCerrada = document.getElementById("RadioCerrada" + num).checked;
    let existe = document.getElementById("ElemntosPreguntas" + (num));

    if (existe != null) {
        if (CkAbierta == true && CkCerrada == true) {

            var e = document.getElementById("TiposPreguntas" + num);
            var e_eliminar = document.getElementById("ElemntosPreguntas" + num);
            var throwawayNode = e.removeChild(e_eliminar);
            $('#RadioCerrada' + num).prop('checked', false);
            $('#TiposPreguntas' + num).append(AgregarAbierta(num));/* "<div class='excerpt' id='Preguntas" + num + "'><p>Hola como estas?</p></div>" */
            ArrayAbiertas[num] = 'true';
            ArrayCerrada[num] = 'false';
            ActualizarRadios();
        }
        return;
    }
    if (CkAbierta == true && CkCerrada == false) {
        $('#TiposPreguntas' + num).append(AgregarAbierta(num));
        $('#RadioCerrada' + num).prop('checked', false);
        ArrayAbiertas[num] = 'true';
        ArrayCerrada[num] = 'false';
        ActualizarRadios();
    }
    var radioButTrat = document.getElementsByName("#RadioCerrada");
    //Lista

    for (var i = 0; i < radioButTrat.length; i++) {

        if (radioButTrat[i].checked == false) {

            ("input[name='#RadioCerrada']:checked").val();

        }

    }
    for (var i = 0; i < radioButTrat.length; i++) {

        if (radioButTrat[i].checked == true) {

            ("input[name='#RadioAbierta']:checked").val();

        }
    }
}


function PreguntaCerrada(num) {
    let CkAbierta = document.getElementById("RadioAbierta" + num).checked;
    let CkCerrada = document.getElementById("RadioCerrada" + num).checked;
    let existe = document.getElementById("ElemntosPreguntas" + (num));
    if (existe != null) {
        if (CkCerrada == true && CkAbierta == true) {
            var e = document.getElementById("TiposPreguntas" + num);
            var e_eliminar = document.getElementById("ElemntosPreguntas" + num);
            var throwawayNode = e.removeChild(e_eliminar);
            $('#RadioAbierta' + num).prop('checked', false);
            $('#TiposPreguntas' + num).append(AgregarCerradas(num));
            ArrayCerrada[num] = 'true';
            ArrayAbiertas[num] = 'false';
            ActualizarRadios();
        }
        return;
    }
    if (CkCerrada == true && CkAbierta == false) {
        $('#TiposPreguntas' + num).append(AgregarCerradas(num));
        $('#RadioAbierta' + num).prop('checked', false);
        ArrayCerrada[num] = 'true';
        ArrayAbiertas[num] = 'false';
        ActualizarRadios();
    }
}


function Agregar(num) {

    let CkAbierta = document.getElementById("RadioAbierta" + num).checked;
    let CkCerrada = document.getElementById("RadioCerrada" + num).checked;
    if (CkAbierta == false && CkCerrada == false) {
        alert("Escoje un tipo de pregunta primero");
    } else {

        $("#Preguntas").append(actualizarplantilla);
        var e_eliminar = document.getElementById("btnAgregar" + (num));
        e_eliminar.parentNode.removeChild(e_eliminar);
        ActualizarRadios();
    }
}

function ActualizarRadios(num) {
    console.clear();
    for (var i = 0; i <= NoPreguntas - 1; i++) {
        if (document.getElementById(i) != null) {
            console.log(i);
            console.log("Abierta:" + i + ArrayAbiertas[i]);
            let pregunta = ArrayAbiertas[i];
            if (pregunta == "false") {
                console.log("false");
                $('#RadioAbierta' + i).prop('checked', false);
            } else if (pregunta == "true") {
                console.log("true");
                $('#RadioAbierta' + i).prop('checked', true);
            }
            console.log("Cerrada:" + i + ArrayCerrada[i]);
            pregunta = ArrayCerrada[i];
            if (pregunta == "false") {
                console.log("false");
                $('#RadioCerrada' + i).prop('checked', false);
            } else if (pregunta == "true") {
                console.log("true");
                $('#RadioCerrada' + i).prop('checked', true);
            }
        }

    }
}

function GuardarExamen() {
    var formData = new FormData();
    formData.append("NoPreguntas", NoPreguntas);
    for(var i=0; i<NoPreguntas;i++){
        var txtPregunta = 'txtPregunta'+ i;
        var pregunta =  document.getElementById(txtPregunta).value;
        formData.append("Pregunta"+i, pregunta);
        var documente = document.querySelector('#FPregunta'+i)
        formData.append("FilePregunta"+i , documente.files[0]);
        
        if(ArrayAbiertas[i]=="true"){
            formData.append("Tipo","Abierta");
            formData.append("RespuestaCorrecta("+i+")", document.getElementById("RespuestaCorrecta("+i+")"));
            formData.append("FileRespuestaCorrecta"+i, document.getElementById("FRespuesta1("+i+")"));
        }
        if(ArrayCerrada[i]=="true"){
            formData.append("Tipo","Cerrada");
            formData.append("RespuestaCorrecta("+i+")", document.getElementById("RespuestaCorrecta("+i+")"));
            formData.append("FRespuestaC"+i, document.getElementById("FRespuestaC"+i));
            formData.append("Opcion1("+i+")", document.getElementById("Respuesta1("+i+")"));
            formData.append("FOpcion1("+i+")", document.getElementById("FRespuesta1("+i+")"));
            formData.append("Opcion2("+i+")", document.getElementById("Respuesta2("+i+")"));
            formData.append("FOpcion2("+i+")", document.getElementById("FRespuesta2("+i+")"));
            formData.append("Opcion3("+i+")", document.getElementById("Respuesta3("+i+")"));
            formData.append("FOpcion3("+i+")", document.getElementById("FRespuesta3("+i+")"));
            formData.append("Opcion4("+i+")", document.getElementById("Respuesta4("+i+")"));
            formData.append("FOpcion4("+i+")", document.getElementById("FRespuesta4("+i+")"));
        }
    }
    console.log(formData);

    axios({
        method: 'post',
        url: 'http://localhost:1234/Escribirtxt',
        data: formData,
        headers: {'Content-Type': 'multipart/form-data'}
    })
    .then(function (response) {
        console.log(response);
    })
    .catch(function (error) {
        console.log(error);
    });


    /*if (document.getElementById(i) != null) {
        if (ArrayAbiertas[i] == "true") {
            formData.append("FRespuesta1("+i+") ", document.getElementById("FRespuesta1("+i+")"));
        } else if (ArrayCerrada[i] == "true") {
            formData.append("FRespuesta1("+i+") ", document.getElementById("FRespuesta1("+i+")"));
            formData.append("FRespuesta2("+i+") ", document.getElementById("FRespuesta2("+i+")"));
            formData.append("FRespuesta3("+i+") ", document.getElementById("FRespuesta3("+i+")"));
            formData.append("FRespuesta4("+i+") ", document.getElementById("FRespuesta4("+i+")"));
        }

    }*/
}

function Quitar(num) {
    if (num != null) {
        var e_eliminar = document.getElementById(num);
        e_eliminar.parentNode.removeChild(e_eliminar);
    }
}

function actualizarplantilla() {
    var plantillapregunta = '<div id="' + NoPreguntas + '" class="media tm-media"><div class="media-body tm-box-5"><h2>¿Cual es la Pregunta?</h2><textarea name="txtPregunta' + NoPreguntas + '" id="txtPregunta' + NoPreguntas + '" cols="30" rows="2"' +
        'placeholder="Pregunta sin titulo"></textarea>  <input type="file" name="FPregunta'+NoPreguntas+'" id="FPregunta'+NoPreguntas+'"><br><ul><li><input type="radio" name="RadioAbierta' + NoPreguntas + '" id="RadioAbierta' + NoPreguntas + '" onclick="PreguntaAbierta(' + NoPreguntas + ')">Abierta</li>' +
        '<li><input type="radio" name="RadioCerrada' + NoPreguntas + '" id="RadioCerrada' + NoPreguntas + '" onclick="PreguntaCerrada(' + NoPreguntas + ')">Cerrada</li></ul><div id="TiposPreguntas' + NoPreguntas + '">' +
        '</div><div id="BotonesPreguntas' + NoPreguntas + '" class="media-body tm-box-5-1"><ul><li><button class="btn" id="btnAgregar' + NoPreguntas + '" type="button" onclick="Agregar(' + NoPreguntas + ')">Aceptar</button>' +
        '</li><li><button class="btn" type="button" onclick="Quitar(' + NoPreguntas + ')">Quitar</button></li></ul></div></div></div>';
    NoPreguntas++;
    return plantillapregunta;
}

function AgregarCerradas(num) {
    var preguntasCerradas = '<div id="ElemntosPreguntas' + num + '" class="ElementosPreguntasCerradas"><ul><li><h3>Respuesta Correcta:</h3></li><li><textarea name="RespuestaCorrecta(' + num + ')" id="RespuestaCorrecta(' + num + ')" cols="30" rows="2" ' +
        'placeholder="Ponga la Respuesta Correcta"></textarea> <input type="file" name="FRespuestaC'+num+'" id="FRespuestaC'+num+'"></li></ul><br><ul><li><h3>Opcion 1:</h3></li><li><textarea name="Respuesta1(' + num + ')" id="Respuesta1(' + num + ')" cols="30" rows="2"' +
        'placeholder="Ponga la Respuesta"></textarea></li></ul><ul><li><input type="file" name=FRespuesta1(' + num + ') id="FRespuesta1(' + num + ')"> </li></ul><ul><li><h3>Opcion 2:</h3>' +
        '</li><li><textarea name="Respuesta2(' + num + ')" id="Respuesta2(' + num + ')" cols="30" rows="2"placeholder="Ponga la Respuesta"></textarea></li></ul><ul><li><input type="file" name=FRespuesta2(' + num + ') id="FRespuesta2(' + num + ')"> ' +
        '</ul><ul><li><h3>Opcion 3:</h3></li><li><textarea name="Respuesta3(' + num + ')" id="Respuesta3(' + num + ')" cols="30" rows="2"placeholder="Ponga la Respuesta"></textarea>' +
        '</li></ul><ul><li><input type="file" name=FRespuesta3(' + num + ') id="FRespuesta3(' + num + ')"> </li></ul><ul><li><h3>Opcion 4:</h3></li><li><textarea name="Respuesta4(' + num + ')" id="Respuesta4(' + num + ')" cols="30" rows="2"' +
        'placeholder="Ponga la Respuesta"></textarea></li></ul><ul><li> <input type="file" name=FRespuesta4(' + num + ') id="FRespuesta4(' + num + ')"> </li></ul></div>';
    return preguntasCerradas;
}

function AgregarAbierta(num) {
    var preguntaAbierta = '<div id="ElemntosPreguntas' + num + '" class="ElementosPreguntasAbiertas"> <ul> <li> <h3>Respuesta Correcta:</h3>' +
        '</li><li><textarea name="RespuestaCorrecta(' + num + ')" id="RespuestaCorrecta(' + num + ')" cols="30" rows="2"placeholder="Ponga la Respuesta"></textarea></li>' +
        '</ul><input type="file" name=FRespuesta1(' + num + ') id="FRespuesta1(' + num + ')"></div>';
    return preguntaAbierta;
}


function ConfirmarExamen() {
    for (var i = 0; i < NoPreguntas; i++) {
        console.log(1);
        if (document.getElementById(i) != null) {
            console.log(2);
            if (document.getElementById("txtPregunta" + i) != null) {
                console.log(3);
                var Pregunta = document.getElementById("txtPregunta" + i).value;
                if (document.getElementById("RadioCerrada" + i).checked == true) {
                    console.log(4);
                    var RespuestaCorrecta = document.getElementById("RespuestaCorrecta(" + i + ")").value;
                    var Respuesta1 = document.getElementById("Respuesta1(" + i + ")").value;
                    var Respuesta2 = document.getElementById("Respuesta2(" + i + ")").value;
                    var Respuesta3 = document.getElementById("Respuesta3(" + i + ")").value;
                    var Respuesta4 = document.getElementById("Respuesta4(" + i + ")").value;
                    console.log(RespuestaCorrecta + ' ' + Respuesta1 + ' ' + Respuesta2 + ' ' + Respuesta3 + ' ' + Respuesta4)
                } else if (document.getElementById("RadioAbierta" + i).checked == true) {
                    var RespuestaCorrecta = document.getElementById("RespuestaCorrecta(" + i + ")").value;
                } else {
                    alert("Selecciona un tipo de pregunta");
                }
            } else {
                alert("Verifica que todos los campos esten llenos");
            }
        }
    }
}