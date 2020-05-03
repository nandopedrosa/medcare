//configs
Vue.config.devtools = true

//Global Functions

//Converte de um UNIX timestamp para dd/mm/yyyy
  var unixToData = function (unixTimestamp) {
    var date = new Date(unixTimestamp * 1000);
    var dia = date.getDate();
    if (dia < 10)
      dia = '0' + dia;
    var mes = date.getMonth() + 1;
    if (mes < 10)
      mes = '0' + mes;
    var ano = date.getFullYear();
    var retorno = dia + '/' + mes + '/' + ano;
    return retorno;
  }

//Components

//Header
Vue.component('meu-header', {  
  template: `
  <header>
        <div class="collapse bg-dark" id="navbarHeader">
            <div class="container">
              <div class="row">
                <div class="col-sm-8 col-md-7 py-4">
                  <h4 class="text-white">Sobre</h4>
                  <p class="text-muted">Medcare é um aplicativo que permite cadastrar e acompanhar pacientes e seus respectivos prontuários e tratamentos médicos. Para dúvidas e sugestões, favor <a href="mailto:fpedrosa@gmail.com?Subject=Medcare" target="_top">entrar em contato</a> com o administrador</p>
                </div>
                <div class="col-sm-4 offset-md-1 py-4">
                  <h4 class="text-white">Opções</h4>
                  <ul class="list-unstyled">                    
                    <li><a href="/logout" class="text-white ml-2">Sair</a></li>                    
                  </ul>
                </div>
              </div>
            </div>
          </div>
        
      <div class="navbar navbar-dark bg-dark box-shadow">
        <div class="container d-flex justify-content-between">
          <a href="/index.html" class="navbar-brand d-flex align-items-center">            
            <strong>Medcare</strong>
          </a>
          <button id="menu-toggler" class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarHeader" aria-controls="navbarHeader" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
        </div>
      </div>
    </header>
  `
})

//Footer
Vue.component('meu-footer', {  
  template: `
  <footer class="text-muted">
      <div class="container">
        <p class="float-right">
          <a href="#">Voltar ao topo</a>
        </p>
        <p>Copyright © Medcare, desde 2020</p>
        
      </div>
    </footer>
  `
})

//Controllers
var header = new Vue({
  el : '#meu-header'
 
})

var footer = new Vue({
  el : '#meu-footer'
})