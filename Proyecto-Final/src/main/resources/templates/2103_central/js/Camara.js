let tiempoInicio, mediaRecorder, idIntervalo;
let videos = new Array();
function comenzarAGrabar(num) {
  const $video = document.querySelector("#video-" + num);
  if (mediaRecorder) return alert("Ya se está grabando");

  navigator.mediaDevices.getUserMedia({
    audio: false, video: true
  })
    .then(stream => {
      // Poner stream en vídeo
      $video.srcObject = stream;
      $video.play();
      // Comenzar a grabar con el stream
      mediaRecorder = new MediaRecorder(stream);
      mediaRecorder.start();
      // En el arreglo pondremos los datos que traiga el evento dataavailable
      const fragmentosDeAudio = [];
      // Escuchar cuando haya datos disponibles
      mediaRecorder.addEventListener("dataavailable", evento => {
        // Y agregarlos a los fragmentos
        fragmentosDeAudio.push(evento.data);
      });
      // Cuando se detenga (haciendo click en el botón) se ejecuta esto
      mediaRecorder.addEventListener("stop", () => {
        // Pausar vídeo
        $video.pause();
        // Detener el stream
        stream.getTracks().forEach(track => track.stop());
        // Detener la cuenta regresiva
        // Convertir los fragmentos a un objeto binario
        const blobVideo = new Blob(fragmentosDeAudio, { 'type': 'video/.mp4; codecs=vp8' });

        // Crear una URL o enlace para descargar
        const urlParaDescargar = URL.createObjectURL(blobVideo);
        // Crear un elemento <a> invisible para descargar el audio
        let a = document.createElement("a");
        document.body.appendChild(a);

        a.id = "Pregunta-" + num;
        a.href = urlParaDescargar;
        a.value = blobVideo;
        a.download = document.getElementById("NombreExamen").value + "-Pregunta" + num + ".mp4";
        // Hacer click en el enlace
        const myFile = new File(
          [blobVideo],
          "Pregunta-" + num,
          { type: 'video/mp4' }
        );

        let nuevo = videos.push(myFile);
        console.log(videos[num]);
        // Y remover el objeto
        window.URL.revokeObjectURL(urlParaDescargar);
      });
    })
    .catch(error => {
      // Aquí maneja el error, tal vez no dieron permiso
      console.log(error)
    });
};


const detenerGrabacion = () => {
  if (!mediaRecorder) return alert("No se está grabando");
  mediaRecorder.stop();
  mediaRecorder = null;
};


function GuardarRespuestas(nombre) {
  var NoPreguntas = document.getElementById("NoPreguntas").value;
  var NombreExamen = document.getElementById("NombreExamen").value;
  var NombreAlumno = document.getElementById("NombreAlumno").value;
  var formData = new FormData();
  formData.append("NoPreguntas", NoPreguntas);
  formData.append("NombreExamen", NombreExamen);
  formData.append("NombreAlumno", NombreAlumno);
  for(var i=0; i<NoPreguntas;i++){
      if(document.getElementById("Tipo-"+i).value== "Abierta"){
          formData.append("Tipo"+i,"Abierta");
          formData.append("RespuestaPregunta-"+i, nombreTxtArea("txtRespuestaAbierta-"+i));
          formData.append("FRespuestaPregunta"+i, videos[i]);
      }
      if(document.getElementById("Tipo-"+i).value == "Cerradas"){
          formData.append("Tipo"+i,"Cerrada");
          for(var j=1; j<=4; j++){
            if(document.getElementById("Opcion"+j+""+i).checked){
              formData.append("RespuestaPregunta-"+i, "opcion"+j);
            }
          }
      }
  }
  console.log(formData);

  axios({
      method: 'post',
      url: 'http://localhost:1234/guardarRespuestas',
      data: formData,
      headers: {'Content-Type': 'multipart/form-data'}
  })
  .then(function (response) {
      console.log(response);
  })
  .catch(function (error) {
      console.log(error);
  });

  setTimeout("history.back(1)", 3000);
}

function nombreTxtArea(name){
  var txtPregunta = name;
  return document.getElementById(txtPregunta).value;
}