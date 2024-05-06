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
	    let pago = 
	    {
			"fechaPago" : document.getElementById("fecha").value,
			"monto" : document.getElementById("monto").value,
			"metodoPago" : document.getElementById("metodo").value,
			"estadoPago" : document.getElementById("estado").value,
			"idCliente" : document.getElementById("Selectcliente").value
		};

	    try 
	    {
            const responseRegistro = await fetch("http://localhost:7700/apispartafit/pagos", {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(pago),
            });

            if (!responseRegistro.ok) 
            {
				alert("No se pudo registrar nuevo cliente");
            }
			else
			{
            	alert("Se logro guardar");
            	window.location.href = "/spartafit/pagos";
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
	    const response = await fetch("http://localhost:7700/apispartafit/pagos");
	    const empleados = await response.json();
	    let content = ``;
	    empleados.forEach((empleado) => {
	      content += `
	                <tr>
	                    <td class="centered">${empleado.idPago}</td>
	                    <td class="centered">${empleado.fechaPago}</td>
	                    <td class="centered">${empleado.monto}</td>
	                    <td class="centered">${empleado.metodoPago}</td>
	                    <td class="centered">${empleado.estadoPago}</td>
	                    <td class="centered">${empleado.idCliente}</td>
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