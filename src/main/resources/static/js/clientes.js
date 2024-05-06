//Registrar la nuevo cliente
	let boton = document.getElementById("btnAceptar");
	boton.addEventListener("click", evento =>
	{
	    newEmpleado();
	});
	
	let newEmpleado = async () =>
	{	
	    let cliente = 
	    {
			"idCliente" : document.getElementById("idcliente").value,
			"nombre" : document.getElementById("nombre").value,
			"direccion" : document.getElementById("direccion").value,
			"telefono" : document.getElementById("telefono").value,
			"correoElectronico" : document.getElementById("correo").value,
			"fechaInicio" : document.getElementById("fechainicio").value,
			"fechaVencimiento" : document.getElementById("fechavencimiento").value,
			"tipoMembresia" : document.getElementById("membresia").value
		};

	    try 
	    {
            const responseRegistro = await fetch("http://localhost:7700/apispartafit/clientes", {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(cliente),
            });

            if (!responseRegistro.ok) 
            {
				alert("No se pudo registrar nuevo cliente");
            }
			else
			{
            	alert("Se logro guardar");
            	window.location.href = "/spartafit/clientes";
			}
	    }
	    catch (error)
	    {
	        alert("No se pudo registrar nuevo cliente");
	        console.error('Error durante el registro: ', error);
	    }
	};
	
//Rellenar tablas
	const listEmpleados= async () => 
	{
	  try 
	  {
	    const response = await fetch("http://localhost:7700/apispartafit/clientes");
	    const empleados = await response.json();
	    let content = ``;
	    empleados.forEach((empleado) => {
	      content += `
	                <tr>
	                    <td class="centered">${empleado.idCliente}</td>
	                    <td class="centered">${empleado.nombre}</td>
	                    <td class="centered">${empleado.direccion}</td>
	                    <td class="centered">${empleado.telefono}</td>
	                    <td class="centered">${empleado.correoElectronico}</td>
	                    <td class="centered">${empleado.fechaInicio}</td>
	                    <td class="centered">${empleado.fechaVencimiento}</td>
	                    <td class="centered">${empleado.tipoMembresia}</td>
	                    <td class="centered">
	                    	<button id="edit" class="btn btn-sm btn-primary" onclick="openEditModal('${empleado.idCliente}')"> <i class="fa-solid fa-pencil"></i></button>
	        				<button id="delete" class="btn btn-sm btn-danger" onclick="eliminar(${empleado.idCliente})"><i class="fa-solid fa-trash-can"></i></button>
	                    </td>
	                </tr>`;
	    });
	    tableBody_empleados.innerHTML = content;
	  } catch (ex) {
	    alert(ex);
	  }
	};
	
	document.addEventListener('DOMContentLoaded', (event) => {
	  listEmpleados();
	});
	
//Editar
async function openEditModal(idCliente) 
{
	try 
	{
    	const response = await fetch(`http://localhost:7700/apispartafit/clientes/${idCliente}`);
    	if (!response.ok) 
    	{
      		alert("Hubo algun error en el url");
    	}
	    const empleadoDetalles = await response.json();
	
	  	const modalBody = document.getElementById("editModalBody");
	  	modalBody.innerHTML = `
  		<div class="mb-3">
		    <label for="recipient-name" class="col-form-label">Id Cliente:</label>
			<input type="text" class="form-control" id="editIdCliente" value="${empleadoDetalles.idCliente}" readonly/>
	    </div>
	    
	    <div class="mb-3">
        	<label for="editNombre" class="col-form-label">Nombre:</label>
        	<input type="text" class="form-control" id="editNombre" value="${empleadoDetalles.nombre}" />
     	</div>
     	
     	<div class="mb-3">
        	<label for="editNombre" class="col-form-label">Direccion:</label>
    		<input type="text" class="form-control" id="editDireccion" value="${empleadoDetalles.direccion}" />
     	</div>
     	
     	correoElectronico
     	<div class="mb-3">
        	<label for="editNombre" class="col-form-label">Telefono:</label>
        	<input type="text" class="form-control" id="editTelefono" value="${empleadoDetalles.telefono}" />
     	</div>
     	
     	<div class="mb-3">
        	<label for="editNombre" class="col-form-label">Correo:</label>
        	<input type="text" class="form-control" id="editCorreo" value="${empleadoDetalles.correoElectronico}" />
     	</div>
     	
     	<div class="mb-3">
        	<label for="editNombre" class="col-form-label">Fecha Inicio:</label>
    		<input type="date" name="editFecha" id="editFechaInicio" value="${empleadoDetalles.fechaInicio}" readonly/>
     	</div>
     	
     	<div class="mb-3">
        	<label for="editNombre" class="col-form-label">Fecha Vencimiento:</label>
    		<input type="date" name="editFecha" id="editFechaVencimiento" value="${empleadoDetalles.fechaVencimiento}" />
     	</div>
     	
     	<div class="mb-3">
        	<label for="editNombre" class="col-form-label">Tipo Membresia:</label>
        	<input type="text" class="form-control" id="editMembresia" value="${empleadoDetalles.tipoMembresia}" />
     	</div>
     	`;
	} 
	catch (error) 
	{
    	console.error('Error durante la obtención de detalles: ', error);
  	}
  		
	const editModal = new bootstrap.Modal(document.getElementById("editModal"));
	editModal.show();
}

//Editar marca (Guardar datos)
let save = document.getElementById("saveChangesBtn");
	save.addEventListener("click", evento =>
	{
	     saveChanges();
	});

async function saveChanges()
{
	const request = 
	{
		"idCliente" : document.getElementById("editIdCliente").value,
		"nombre" : document.getElementById("editNombre").value,
		"direccion" : document.getElementById("editDireccion").value,
		"telefono" : document.getElementById("editTelefono").value,
		"correoElectronico" : document.getElementById("editCorreo").value,
		"fechaInicio" : document.getElementById("editFechaInicio").value,
		"fechaVencimiento" : document.getElementById("editFechaVencimiento").value,
		"tipoMembresia" : document.getElementById("editMembresia").value
	}
	
	try 
	{
	    const response = await fetch(`http://localhost:7700/apispartafit/clientes`, {
	      method: 'PUT',
	      headers: {
	        'Content-Type': 'application/json',
	      },
	      body: JSON.stringify(request),
	    });
	
	    if (!response.ok) 
	    {
	      alert("No se pudo modificar al cliente");
	    }
		
	    alert("Se guardo la edicion");
	    window.location.href="/spartafit/clientes";
	}
	catch (error) 
	{console.error('Error al actualizar: ', error);}
	
	const editModal = bootstrap.Modal.getInstance(
		document.getElementById("editModal")
	);
	
	editModal.hide();
}	

//Eliminar datos
async function eliminar(idCliente)
{
		
	try
	{
		const borrar = await fetch("http://localhost:7700/apispartafit/clientes/"+idCliente, {
			method: "DELETE",
	        headers: 
	        {
	            'Accept': 'application/json',
	            'Content-Type': 'application/json'
	        }
    	});
	    if (borrar.ok) 
	    {
   			alert("Clienteeliminado");
   			window.location.href="/spartafit/clientes";
		} 
		else 
		{
   			alert("No se pudo eliminar al cliente");
	   	}
	}
	catch(error)
	{
		alert("No se pudo eliminar al cliente");
        console.error('Error during delete: ', error);
	}
};   