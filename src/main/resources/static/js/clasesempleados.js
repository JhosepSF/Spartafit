//Cargar Clientes
const selectEmpleados = document.getElementById("Selectempleados");
fetch("http://localhost:7700/apispartafit/empleados")
    .then(response => response.json())
    .then(empleados => 
    {
        empleados.forEach(empleado => 
        {
            const option = document.createElement("option");
            option.value = empleado.idEmpleado;
            option.textContent = empleado.nombre;
            selectEmpleados.appendChild(option);
        });
    })
    .catch(error => console.error("Error al obtener la lista de empleados:", error));
 
//Cargar Clientes
const selectClases = document.getElementById("Selectclases");
fetch("http://localhost:7700/apispartafit/clases")
    .then(response => response.json())
    .then(clases => 
    {
        clases.forEach(clase => 
        {
            const option = document.createElement("option");
            option.value = clase.idClase;
            option.textContent = clase.nombre;
            selectClases.appendChild(option);
        });
    })
    .catch(error => console.error("Error al obtener la lista de clases:", error));

//Registrar la nueva Marca
	let boton = document.getElementById("btnAceptar");
	boton.addEventListener("click", evento =>
	{
	    newEmpleado();
	});
	
	let newEmpleado = async () =>
	{	
	    let ce = 
	    {
			"idClase" : document.getElementById("Selectclases").value,
			"idEmpleado" : document.getElementById("Selectempleados").value
		};

	    try 
	    {
            const responseRegistro = await fetch("http://localhost:7700/apispartafit/clasesempleados", {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(ce),
            });

            if (!responseRegistro.ok) 
            {
				alert("No se pudo registrar nueva relacion");
            }
			else
			{
            	alert("Se logro guardar");
            	window.location.href = "/spartafit/clasesempleado";
			}
	    }
	    catch (error)
	    {
	        alert("No se pudo registrar nueva relacion");
	        console.error('Error durante el registro: ', error);
	    }
	};
	
//Rellenar tablas
	const listEmpleados= async () => 
	{
	  try 
	  {
	    const response = await fetch("http://localhost:7700/apispartafit/clasesempleados");
	    const empleados = await response.json();
	    let content = ``;
	    empleados.forEach((empleado) => {
	      content += `
	                <tr>
	                    <td class="centered">${empleado.idClaseEmpleado}</td>
	                    <td class="centered">${empleado.idClase}</td>
	                    <td class="centered">${empleado.idEmpleado}</td>
	                </tr>`;
	    });
	    tableBody_empleados.innerHTML = content;
	  } catch (ex) {
	    alert(ex);
	  }
	};
	
	document.addEventListener('DOMContentLoaded', (event) => 
	{
	  listEmpleados();
	});