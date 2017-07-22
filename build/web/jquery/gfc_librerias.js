 function gfc_comprobar_fecha(f){
            
            /* la forma de verificar el formato es la que ya comentamos */
            re=/^[0-9][0-9]\/[0-9][0-9]\/[0-9][0-9][0-9][0-9]$/
            if(f.length==0 || !re.exec(f))
            {
                    alert("La fecha no tiene formato correcto.")
                    return 0
            }

            /* comprobamos que la fecha es válida */
            var d = new Date()
            /* la función tiene como entrada: año, mes, día */
            d.setFullYear(f.substring(6,10), 
                          f.substring(3,5)-1,
                          f.substring(0,2))

            /* ¿el mes del objeto Date es el mes introducido por el usuario?
               OJO: getMonth() devuelve el número de mes del 0 al 11

               ¿el día del objeto Date es el día introducido por el usuario?
               OJO: getDate() devuelve el día del mes */
            if(d.getMonth() != f.substring(3,5)-1 
                    || d.getDate() != f.substring(0,2))
            {
                    alert("Fecha no válida.")
                    return 0
            }
            
            return 1
        }
        
        
        function gfc_validar_numero(campo,valor){
 
            if  (  isNaN(valor)  || (valor.length==0) )  {
                alert("Valor en " + campo + " no es correcto.");
                return false;
            }
            else
            {
                return true;
            }    
        }        
        
        
        function global_JQuery_html(pmtTipoIdentificador, pmtDescripcionIdentificador, html)
        {
            $("#"+pmtDescripcionIdentificador).html("");
            $("#"+pmtDescripcionIdentificador).empty().html(html);
        }

        function global_generarHTMLImagenLoad()
        {
            //alert("ingreso");
            var cadena;
            cadena = '<img src="'+devolverContextPath()+'img/cargando.gif "/>';
            // alert(cadena+"ingreso 1")
            return cadena;
        }

        function devolverContextPath()
        {
            return $("#mycontextpath").text();
        }

// function confirmar(mensaje)
//{
//	if(confirm('¿Estas seguro de '+mensaje+'?'))
//		return true;
//	else
//		return false;
//}
// 