let tiempoInicio, mediaRecorder, idIntervalo;
let 
function comenzarAGrabar(num){
  const $video = document.querySelector("#video-"+num);
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
              a.style = "display: none";
              a.href = urlParaDescargar;
              a.download = document.getElementById("NombreExamen").value + "-Pregunta" + num + ".mp4";
              // Hacer click en el enlace
              console.log(blobVideo);
              a.click();
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


function GuardarRespuestas(){
  var documente = document.querySelector("video-0");
  var Video = documente.files[0];
  console.log(Video);
}