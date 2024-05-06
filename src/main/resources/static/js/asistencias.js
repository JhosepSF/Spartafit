//Establacer min y max de fechas
const fechaActual = new Date();
const primerDiaDelMes = new Date(fechaActual.getFullYear(), fechaActual.getMonth(), 1);
const formattedMinDate = primerDiaDelMes.toISOString().split('T')[0];
const hoy = new Date().toISOString().split('T')[0];

document.getElementById("fecha").setAttribute("min", formattedMinDate);
document.getElementById("fecha").setAttribute("max", hoy);

var horaActual = new Date().toLocaleTimeString('es-ES', {hour: '2-digit', minute: '2-digit'});
document.getElementById("hora").value = horaActual;

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
    
 //Cargar CE
const selectCE = document.getElementById("Selectce");
fetch("http://localhost:7700/apispartafit/clasesempleados")
    .then(response => response.json())
    .then(ces => 
    {
        ces.forEach(ce => 
        {
            const option = document.createElement("option");
            option.value = ce.idClaseEmpleado;
            option.textContent = ce.idClase + " - " + ce.idEmpleado;
            selectCE.appendChild(option);
        });
    })
    .catch(error => console.error("Error al obtener la lista de CEs:", error));

//Registrar la nueva Marca
	let boton = document.getElementById("btnAceptar");
	boton.addEventListener("click", evento =>
	{
	    newEmpleado();
	});
	
	let newEmpleado = async () =>
	{	
	    let asistencia =
	    {
			"fecha" : document.getElementById("fecha").value,
			"hora" : document.getElementById("hora").value,
			"idCliente" : document.getElementById("Selectcliente").value,
			"idClaseEmpleado" : document.getElementById("Selectce").value,
		};

	    try 
	    {
            const responseRegistro = await fetch("http://localhost:7700/apispartafit/asistencias", {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(asistencia),
            });

            if (!responseRegistro.ok) 
            {
				alert("No se pudo registrar nueva asistencia");
            }
			else
			{
            	alert("Se logro guardar");
            	window.location.href = "/spartafit/asistencias";
			}
	    }
	    catch (error)
	    {
	        alert("No se pudo registrar nuevo asistencia");
	        console.error('Error durante el registro: ', error);
	    }
	};
	
//Rellenar tablas
	const listEmpleados= async () => 
	{
	  try 
	  {
	    const response = await fetch("http://localhost:7700/apispartafit/asistencias");
	    const empleados = await response.json();
	    let content = ``;
	    empleados.forEach((empleado) => {
	      content += `
	                <tr>
	                    <td class="centered">${empleado.idAsistencia}</td>
	                    <td class="centered">${empleado.fecha}</td>
	                    <td class="centered">${empleado.hora}</td>
	                    <td class="centered">${empleado.idCliente}</td>
	                    <td class="centered">${empleado.idClaseEmpleado}</td>
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