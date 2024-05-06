//Establacer min y max de fechas
const fechaActual = new Date();
const primerDiaDelMes = new Date(fechaActual.getFullYear(), fechaActual.getMonth(), 1);
const formattedMinDate = primerDiaDelMes.toISOString().split('T')[0];
const hoy = new Date().toISOString().split('T')[0];

document.getElementById("fecha").setAttribute("min", formattedMinDate);
document.getElementById("fecha").setAttribute("max", hoy);

//Cargar Clientes
const selectCliente = document.getElementById("Selectcliente");
fetch("http://localhost:7700/apispartafit/clientes")
    .then(response => response.json())
    .then(clientes => 
    {
        clientes.forEach(cliente => 
        {
            const option = document.createElement("option");
            option.value = cliente.idCliente;
            option.textContent = cliente.nombre;
            selectCliente.appendChild(option);
        });
    })
    .catch(error => console.error("Error al obtener la lista de clientes:", error));

//Registrar la nueva Marca
	let boton = document.getElementById("btnAceptar");
	boton.addEventListener("click", evento =>
	{
	    newEmpleado();
	});
	
	let newEmpleado = async () =>
	{	
	    let eva = 
	    {
			"fechaEvaluacion" : document.getElementById("fecha").value,
			"peso" : document.getElementById("peso").value,
			"altura" : document.getElementById("altura").value,
			"imc" : document.getElementById("imc").value,
			"idCliente" : document.getElementById("Selectcliente").value
		};

	    try 
	    {
            const responseRegistro = await fetch("http://localhost:7700/apispartafit/evaluacionesfisicas", {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(eva),
            });

            if (!responseRegistro.ok) 
            {
				alert("No se pudo registrar nueva evaluacion");
            }
			else
			{
            	alert("Se logro guardar");
            	window.location.href = "/spartafit/evaluaciones";
			}
	    }
	    catch (error)
	    {
	        alert("No se pudo registrar nuevo evaluacion");
	        console.error('Error durante el registro: ', error);
	    }
	};
	
//Rellenar tablas
	const listEmpleados= async () => 
	{
	  try 
	  {
	    const response = await fetch("http://localhost:7700/apispartafit/evaluacionesfisicas");
	    const empleados = await response.json();
	    let content = ``;
	    empleados.forEach((empleado) => {
	      content += `
	                <tr>
	                    <td class="centered">${empleado.idEvaluacionFisica}</td>
	                    <td class="centered">${empleado.idCliente}</td>
	                    <td class="centered">${empleado.fechaEvaluacion}</td>
	                    <td class="centered">${empleado.peso}</td>
	                    <td class="centered">${empleado.altura}</td>
	                    <td class="centered">${empleado.imc}</td>
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