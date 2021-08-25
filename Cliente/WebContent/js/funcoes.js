var rootURL = "http://localhost:8080/Servidor/Servidor/funcoes";

function cadastraUsuario(name, phone, callback) {
	
	if (name != null && name != "") {
		$.ajax({
			type : 'POST',
			url : rootURL + '/cadastro/' + name + '/' + phone,
			dataType : "json",
			success : callback
		});
	}
	
	/*$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : rootURL + '/cadastro/' + '?contactName=' + name + '?telefone=' + phone,
		dataType : "json",
		data : cliente,
		success : callback,
		error : function(jqXHR, textStatus, errorThrown) {
			alert('Erro criando cliente: ' + jqXHR.responseText);
		}
	});*/
}

function listaCaronas(origem, destino, data, callback) {

	if (origem != null && origem != "") {
		$.ajax({
			type : 'GET',
			url : rootURL + '/busca/' + origem + '/' + destino + '/' + data,
			dataType : "text",
			success : callback
		});
	} 

}

function cadastraCarona(nome, telefone, origem, destino, data, passageiros, callback) {
	
	if (nome != null && nome != "") {
		$.ajax({
			type : 'POST',
			url : rootURL + '/cadastroCarona/' + nome + '/' + telefone + '/' + origem + '/' + destino + '/' + data + '/' + passageiros,
			dataType : "text",
			success : callback
		});
	}
}

function cancelaCarona(id, callback) {
	$.ajax({
		type : 'DELETE',
		url : rootURL + '/cancelaCarona/' + '?id=' + id,
		success : callback,
		error : function(jqXHR, textStatus, errorThrown) {
			alert('Erro excluindo carona! ' + textStatus);
		}
	});
}

function cadastraInteresse(nome, telefone, origem, destino, data, callback) {
	
	if (nome != null && nome != "") {
		$.ajax({
			type : 'POST',
			url : rootURL + '/cadastroInteresse/' + nome + '/' + telefone + '/' + origem + '/' + destino + '/' + data,
			dataType : "text",
			success : callback
		});
	}
	
}

function cancelaInteresse(id, callback) {
	$.ajax({
		type : 'DELETE',
		url : rootURL + '/cancelaInteresse/' + '?id=' + id,
		success : callback,
		error : function(jqXHR, textStatus, errorThrown) {
			alert('Erro excluindo interesse! ' + textStatus);
		}
	});
}
