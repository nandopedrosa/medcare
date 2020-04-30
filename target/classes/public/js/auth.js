var producao = true;

//Verifica se um usuário está logado. Se não estiver, redireciona para a página de login
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

