var grabar = document.getElementById("grabar")
grabar.addEventListener("click", GRABAR)
var detener = document.getElementById("detener")
detener.addEventListener("click", DETENER)

var chunks = [];
var mediaRecorder;

navigator.mediaDevices.getUserMedia({
  audio: false, video: true
}).then(function (x) {
  /* usar el flujo de datos */
  console.log(x)
  mediaRecorder = new MediaRecorder(x);

  var camara = document.getElementById("camara");
  camara.srcObject = x;
  camara.onloadedmetadata = function (e) {
    // Do something with the video here.
    camara.play()
  };
  console.log(camara)

  mediaRecorder.onstop = function (e) {
    console.log("data available after MediaRecorder.stop() called.");

    var clipName = prompt('Enter a name for your sound clip');

    var clipContainer = document.createElement('article');
    var clipLabel = document.createElement('p');
    var audio = document.createElement('video');
    audio.width="150"
    var deleteButton = document.createElement('button');
    // generar una liga
    var a = document.createElement('a')
    var texto = document.createTextNode("descarga")
    a.appendChild(texto)

    clipContainer.classList.add('clip');
    audio.setAttribute('controls', '');
    deleteButton.innerHTML = "Delete";
    clipLabel.innerHTML = clipName;

    var soundClips = document.getElementById("xxx")
    clipContainer.appendChild(audio);
    clipContainer.appendChild(clipLabel);
    clipContainer.appendChild(deleteButton);
    clipContainer.appendChild(a)
    soundClips.appendChild(clipContainer);

    audio.controls = true;
    var blob = new Blob(chunks, { 'type': 'video/webm; codecs=vp8' });
    chunks = [];
    var audioURL = URL.createObjectURL(blob);
    audio.src = audioURL;
    console.log("recorder stopped");
    a.href = audioURL
    a.download = "video.mp4"

    deleteButton.onclick = function(e) {
      evtTgt = e.target;
      evtTgt.parentNode.parentNode.removeChild(evtTgt.parentNode);
    }
  }

  mediaRecorder.ondataavailable = function(e) {
    chunks.push(e.data);
    enviar(e.data)
  }

  }).catch(function (err) {
    /* manejar el error */
    console.log(err)
  });

function GRABAR(params) {
  mediaRecorder.start();
  console.log(mediaRecorder.state);
  console.log("recorder started");
  // record.style.background = "red";
  // record.style.color = "black";
}

function DETENER(params) {
  mediaRecorder.stop();
  console.log(mediaRecorder.state);
  console.log("recorder stopped");
  // record.style.background = "";
  // record.style.color = "";
}

function enviar(stream) {
  var formData = new FormData();
  formData.append("videoGrabado", stream)
  axios.post("http://localhost:4567/", formData, {
    headers : {
      "Content-Type" : "multipart/form-data"
    }
  })
}