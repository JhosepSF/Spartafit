//Registrar la nueva Marca
	let boton = document.getElementById("btnAceptar");
	boton.addEventListener("click", evento =>
	{
	    newEmpleado();
	});
	
	let newEmpleado = async () =>
	{	
	    let clase = 
	    {
			"horario" : document.getElementById("horario").value,
			"nombre" : document.getElementById("nombre").value,
			"ubicacion" : document.getElementById("ubicacion").value
		};

	    try 
	    {
            const responseRegistro = await fetch("http://localhost:7700/apispartafit/clases", {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(clase),
            });

            if (!responseRegistro.ok) 
            {
				alert("No se pudo registrar nueva clase");
            }
			else
			{
            	alert("Se logro guardar");
            	window.location.href = "/spartafit/clases";
			}
	    }
	    catch (error)
	    {
	        alert("No se pudo registrar nueva clase");
	        console.error('Error durante el registro de nueva clase: ', error);
	    }
	};
	
//Rellenar tablas
	const listEmpleados= async () => 
	{
	  try 
	  {
	    const response = await fetch("http://localhost:7700/apispartafit/clases");
	    const empleados = await response.json();
	    let content = ``;
	    empleados.forEach((empleado) => {
	      content += `
	                <tr>
	                    <td class="centered">${empleado.idClase}</td>
	                    <td class="centered">${empleado.horario}</td>
	                    <td class="centered">${empleado.nombre}</td>
	                    <td class="centered">${empleado.ubicacion}</td>
	                    <td class="centered">
	                    	<button id="edit" class="btn btn-sm btn-primary" onclick="openEditModal('${empleado.idClase}')"> <i class="fa-solid fa-pencil"></i></button>
	        				<button id="delete" class="btn btn-sm btn-danger" onclick="eliminar(${empleado.idClase})"><i class="fa-solid fa-trash-can"></i></button>
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
async function openEditModal(idClase) 
{
	try 
	{
    	const response = await fetch(`http://localhost:7700/apispartafit/clases/${idClase}`);
    	if (!response.ok) 
    	{
      		alert("Hubo algun error en el url");
    	}
	    const empleadoDetalles = await response.json();
	
	  	const modalBody = document.getElementById("editModalBody");
	  	modalBody.innerHTML = `
  		<div class="mb-3">
		    <label for="recipient-name" class="col-form-label">Id Clase:</label>
			<input type="text" class="form-control" id="editIdClase" value="${empleadoDetalles.idClase}" readonly/>
	    </div>
	    
	    <div class="mb-3">
        	<label for="editNombre" class="col-form-label">Horario:</label>
        	<input type="text" class="form-control" id="editHorario" value="${empleadoDetalles.horario}" />
     	</div>
     	
     	<div class="mb-3">
        	<label for="editNombre" class="col-form-label">Nombre:</label>
    		<input type="text" class="form-control" id="editNombre" value="${empleadoDetalles.nombre}" />
     	</div>
     	
     	<div class="mb-3">
        	<label for="editNombre" class="col-form-label">Ubicacion:</label>
        	<input type="text" class="form-control" id="editUbicacion" value="${empleadoDetalles.ubicacion}" />
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
		"idClase" : document.getElementById("editIdClase").value,
		"horario" : document.getElementById("editHorario").value,
		"nombre" : document.getElementById("editNombre").value,
		"ubicacion" : document.getElementById("editUbicacion").value
	}
	
	try 
	{
	    const response = await fetch(`http://localhost:7700/apispartafit/clases`, {
	      method: 'PUT',
	      headers: {
	        'Content-Type': 'application/json',
	      },
	      body: JSON.stringify(request),
	    });
	
	    if (!response.ok) 
	    {
	      alert("No se pudo modificar la clase");
	    }
		
	    alert("Se guardo la edicion");
	    window.location.href="/spartafit/clases";
	}
	catch (error) 
	{console.error('Error al actualizar: ', error);}
	
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
		const borrar = await fetch("http://localhost:7700/apispartafit/clases/"+idEmpleado, {
			method: "DELETE",
	        headers: 
	        {
	            'Accept': 'application/json',
	            'Content-Type': 'application/json'
	        }
    	});
	    if (borrar.ok) 
	    {
   			alert("Clase eliminado");
   			window.location.href="/spartafit/clases";
		} 
		else 
		{
   			alert("No se pudo eliminar la clase");
	   	}
	}
	catch(error)
	{
		alert("No se pudo eliminar al clase");
        console.error('Error during delete: ', error);
	}
};   