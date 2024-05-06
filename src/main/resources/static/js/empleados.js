//Registrar la nueva Marca
	let boton = document.getElementById("btnAceptar");
	boton.addEventListener("click", evento =>
	{
	    newEmpleado();
	});
	
	let newEmpleado = async () =>
	{	
	    let empleado = 
	    {
			"idEmpleado" : document.getElementById("dni").value,
			"nombre" : document.getElementById("nombre").value,
			"fechaContratacion" : document.getElementById("fecha").value,
			"salario" : document.getElementById("salario").value,
			"horarioTrabajo" : document.getElementById("turno").value
		};

	    try 
	    {
            const responseRegistro = await fetch("http://localhost:7700/apispartafit/empleados", {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(empleado),
            });

            if (!responseRegistro.ok) 
            {
				alert("No se pudo registrar nueva empleado");
            }
			else
			{
            	console.log("Se logro guardar");
            	window.location.href = "/spartafit/empleados";
			}
	    }
	    catch (error)
	    {
	        alert("No se pudo registrar nueva empleado");
	        console.error('Error durante el registro de nuevo empleado:', error);
	    }
	};
	
//Rellenar tablas
	const listEmpleados= async () => 
	{
	  try 
	  {
	    const response = await fetch("http://localhost:7700/apispartafit/empleados");
	    const empleados = await response.json();
	    let content = ``;
	    empleados.forEach((empleado) => {
	      content += `
	                <tr>
	                    <td class="centered">${empleado.idEmpleado}</td>
	                    <td class="centered">${empleado.nombre}</td>
	                    <td class="centered">${empleado.fechaContratacion}</td>
	                    <td class="centered">${empleado.salario}</td>
	                    <td class="centered">${empleado.horarioTrabajo}</td>
	                    <td class="centered">
	                    	<button id="edit" class="btn btn-sm btn-primary" onclick="openEditModal('${empleado.idEmpleado}')"> <i class="fa-solid fa-pencil"></i></button>
	        				<button id="delete" class="btn btn-sm btn-danger" onclick="eliminar(${empleado.idEmpleado})"><i class="fa-solid fa-trash-can"></i></button>
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
async function openEditModal(idEmpleado) 
{
	try 
	{
    	const response = await fetch(`http://localhost:7700/apispartafit/empleados/${idEmpleado}`);
    	if (!response.ok) 
    	{
      		alert("Hubo algun error en el url");
    	}
	    const empleadoDetalles = await response.json();
	
	  	const modalBody = document.getElementById("editModalBody");
	  	modalBody.innerHTML = `
  		<div class="mb-3">
		    <label for="recipient-name" class="col-form-label">Id Empleado:</label>
			<input type="text" class="form-control" id="editIdEmpleado" value="${empleadoDetalles.idEmpleado}" readonly/>
	    </div>
	    
	    <div class="mb-3">
        	<label for="editNombre" class="col-form-label">Nombre:</label>
        	<input type="text" class="form-control" id="editNombre" value="${empleadoDetalles.nombre}" />
     	</div>
     	
     	<div class="mb-3">
        	<label for="editNombre" class="col-form-label">Fecha Contratacion:</label>
    		<input type="date" id="editFecha" name="editFecha" value="${empleadoDetalles.fechaContratacion}" />
     	</div>
     	
     	<div class="mb-3">
        	<label for="editNombre" class="col-form-label">Salario:</label>
        	<input type="text" class="form-control" id="editSalario" value="${empleadoDetalles.salario}" />
     	</div>
     	
     	<div class="mb-3">
        	<label for="editNombre" class="col-form-label">Turno:</label>
        	<input type="text" class="form-control" id="editTurno" value="${empleadoDetalles.horarioTrabajo}" />
     	</div>
     	`;
	} 
	catch (error) 
	{
    	console.error('Error durante la obtención de detalles de la marca:', error);
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
		"idEmpleado" : document.getElementById("editIdEmpleado").value,
		"nombre": document.getElementById("editNombre").value,
		"fechaContratacion": document.getElementById("editFecha").value,
		"salario": document.getElementById("editSalario").value,
		"horarioTrabajo": document.getElementById("editTurno").value
	}
	
	try 
	{
	    const response = await fetch(`http://localhost:7700/apispartafit/empleados`, {
	      method: 'PUT',
	      headers: {
	        'Content-Type': 'application/json',
	      },
	      body: JSON.stringify(request),
	    });
	
	    if (!response.ok) 
	    {
	      alert("No se pudo modificar al empleado");
	    }
		
	    alert("Se guardo la edicion");
	    window.location.href="/spartafit/empleados";
	}
	catch (error) 
	{console.error('Error al actualizar la marca:', error);}
	
	const editModal = bootstrap.Modal.getInstance(
		document.getElementById("editModal")
	);
	
	editModal.hide();
}	

//Eliminar datos
async function eliminar(idEmpleado)
{
		
	try
	{
		const borrar = await fetch("http://localhost:7700/apispartafit/empleados/"+idEmpleado, {
			method: "DELETE",
	        headers: 
	        {
	            'Accept': 'application/json',
	            'Content-Type': 'application/json'
	        }
    	});
	    if (borrar.ok) 
	    {
   			alert("Empleado eliminado");
   			window.location.href="/spartafit/empleados";
		} 
		else 
		{
   			alert("No se pudo eliminar al empleado");
	   	}
	}
	catch(error)
	{
		alert("No se pudo eliminar al empleado");
        console.error('Error during delete:', error);
	}
};   