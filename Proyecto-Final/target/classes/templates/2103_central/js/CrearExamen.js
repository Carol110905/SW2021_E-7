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

    for (var i=0; i<radioButTrat.length; i++) {

    if (radioButTrat[i].checked == false) {

        ("input[name='#RadioCerrada']:checked").val(); 

    }

    }
    for (var i=0; i<radioButTrat.length; i++) {

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
    var informacion="";
    for (var i = 0; i <= NoPreguntas; i++) {
        if (document.getElementById(i) != null) {
            informacion += "Pregunta" + i + ": " + document.getElementById("txtPregunta" + i).value + "\n";
            if (ArrayAbiertas[i] == "true") {
                informacion += "Tipo" + i + ": Abierta" + "\n";
                informacion += "RespuestaCorrecta" + i + ": " + document.getElementById("RespuestaCorrecta(" + i + ")").value + "\n";
            } else if (ArrayCerrada[i] == "true") {
                informacion += "Tipo" + i + ": Cerrada" + "\n";
                informacion += "RespuestaCorrecta" + i + ": " + document.getElementById("RespuestaCorrecta(" + i + ")").value + "\n";
                informacion += "Respuesta1(" + i + "): " + document.getElementById("Respuesta1(" + i + ")").value + "\n";
                informacion += "Respuesta2(" + i + "): " + document.getElementById("Respuesta2(" + i + ")").value + "\n";
                informacion += "Respuesta3(" + i + "): " + document.getElementById("Respuesta3(" + i + ")").value + "\n";
                informacion += "Respuesta4(" + i + "): " + document.getElementById("Respuesta4(" + i + ")").value + "\n";
            }

        }
    }
    console.log(informacion);

    var formData = new FormData();
    formData.append("Informacion", informacion)
    if (document.getElementById(i) != null) {
        if (ArrayAbiertas[i] == "true") {
            formData.append("FRespuesta1("+i+") ", document.getElementById("FRespuesta1("+i+")"));
        } else if (ArrayCerrada[i] == "true") {
            formData.append("FRespuesta1("+i+") ", document.getElementById("FRespuesta1("+i+")"));
            formData.append("FRespuesta2("+i+") ", document.getElementById("FRespuesta2("+i+")"));
            formData.append("FRespuesta3("+i+") ", document.getElementById("FRespuesta3("+i+")"));
            formData.append("FRespuesta4("+i+") ", document.getElementById("FRespuesta4("+i+")"));
        }

    }
    axios.post("http://localhost:1234/Escribirtxt", formData, {
        headers: {
            "Content-Type": "multipart/form-data"
        }
    })
}


function writeToFile(data) {
    var fso = new ActiveXObject("Scripting.FileSystemObject");
    var fh = fso.OpenTextFile("data.txt", 8);
    fh.WriteLine(data.id + ',' + data.content);
    fh.Close();
}

function Quitar(num) {
    if (num != null) {
        var e_eliminar = document.getElementById(num);
        e_eliminar.parentNode.removeChild(e_eliminar);
    }
}

function actualizarplantilla() {
    var plantillapregunta = '<div id="' + NoPreguntas + '" class="media tm-media"><div class="media-body tm-box-5"><h2>¿Cual es la Pregunta?</h2><textarea name="Ponga Su Pregunta" id="txtPregunta' + NoPreguntas + '" cols="30" rows="2"' +
        'placeholder="Pregunta sin titulo"></textarea><ul><li><input type="radio" name="Abierta" id="RadioAbierta' + NoPreguntas + '" onclick="PreguntaAbierta(' + NoPreguntas + ')">Abierta</li>' +
        '<li><input type="radio" name="Cerrada" id="RadioCerrada' + NoPreguntas + '" onclick="PreguntaCerrada(' + NoPreguntas + ')">Cerrada</li></ul><div id="TiposPreguntas' + NoPreguntas + '">' +
        '</div><div id="BotonesPreguntas' + NoPreguntas + '" class="media-body tm-box-5-1"><ul><li><button class="btn" id="btnAgregar' + NoPreguntas + '" type="button" onclick="Agregar(' + NoPreguntas + ')">Aceptar</button>' +
        '</li><li><button class="btn" type="button" onclick="Quitar(' + NoPreguntas + ')">Quitar</button></li></ul></div></div></div>';
    NoPreguntas++;
    return plantillapregunta;
}

function AgregarCerradas(num) {
    var preguntasCerradas = '<div id="ElemntosPreguntas' + num + '" class="ElementosPreguntasCerradas"><ul><li><h3>Respuesta Correcta:</h3></li><li><textarea name="RespuestaCorrecta(' + num + ')" id="RespuestaCorrecta(' + num + ')" cols="30" rows="2" ' +
        'placeholder="Ponga la Respuesta Correcta"></textarea></li></ul><ul><li><h3>Opcion 1:</h3></li><li><textarea name="Respuesta1(' + num + ')" id="Respuesta1(' + num + ')" cols="30" rows="2"' +
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