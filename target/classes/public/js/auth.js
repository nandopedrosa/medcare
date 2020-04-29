var producao = true;

if(producao) {
    $.ajax({
        type: 'GET',              
        url: '/logado',    
        success: function(response){
            if(response.status == 'ERRO') {
                window.location.href = "/login.html";
            }            
        }
      });
}

