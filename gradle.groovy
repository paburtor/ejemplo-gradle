/*
	forma de invocación de método call:
	def ejecucion = load 'script.groovy'
	ejecucion.call()
*/

def call()
{
	echo '${env.WORKSPACE}'
}

return this;
