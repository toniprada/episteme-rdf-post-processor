package com.episteme.datasource.postprocessor;

public class CNAE {

	public static String getCode(String skill) {
		skill = skill.toLowerCase();
		switch (skill) {
		// Informatica
		case "accesorios de informática":
		case "accesorios y consumibles para informática":
		case "alquiler de equipos informáticos":
		case "cartuchos":
		case "consumibles informáticos":
		case "disco duro":
		case "equipos de informática":
		case "escáner":
		case "fotocopiadoras":
		case "hardware":
		case "material informático":
		case "microprocesador":
		case "monitor":
		case "monitores lcd":
		case "ordenadores":
		case "ordenadores segunda mano":
		case "periféricos":
		case "placas base":
		case "portátiles":
		case "sistemas":
		case "software":
		case "tarjetas gráficas":
		case "tpv":
			return "J6203";
		case "asistencia técnica informática":
		case "electrónica":
		case "empresas de informática":
		case "informática y ofimática":
		case "instalación de equipos informáticos":
		case "introducción a informática":
		case "servicios informáticos":
		case "tiendas de informática":
			return "J6209";
		case "desarrollo de programa a medida":
		case "programación informática":
			return "J6201";
		case "dominios":
			return "J6311";
		case "adul":
		case "amplificador":
		case "antenas":
		case "antenas colectivas":
		case "aparatos de telecomunicación":
		case "artículos de telefonía":
		case "asesoría en telecomunicación":
		case "audio, sonido y tiendas de sonido":
		case "cableados":
		case "cableados estructurados":
		case "cables":
		case "chive café":
		case "cibera y chive cafés":
		case "comunicaciones":
		case "domótica":
		case "fibra óptica":
		case "grabadores":
		case "ingeniería en telecomunicaciones":
		case "ingeniero de telecomunicaciones":
		case "instalación antenas":
		case "instalación de telecomunicación":
		case "instalación telefónica":
		case "instalaciones de red de voz y datos":
		case "locutorio":
		case "locutorio con internet":
		case "medios de comunicación":
		case "multimedia":
		case "porteros automáticos":
		case "redes":
		case "redes datos":
		case "redes informáticas":
		case "redes voz":
		case "servicio técnico telecomunicación":
		case "servicios de telecomunicaciones":
		case "tarificadores":
		case "telefonía":
		case "telefonía ip":
		case "telegrafía":
		case "voip":
			return "J6190";
		case "antenas parabólicas":
		case "parabólica":
			return "J6130";
		case "banda ancha":
		case "cableado de voz":
		case "centrales telefónicas":
		case "centralitas telefónicas ip":

			return "J6110";
		case "instalación tdt":
		case "tdt":
		case "televisión digital terrestre":
		case "televisiones":
			return "J6020";
		case "internet":
		case "páginas web":
		case "web":
			return "J6312";
		case "radio":
		case "radiocomunicaciones":
			return "J6010";
		case "telefonía movil":
		case "teléfonos móviles":
		case "wifi":
		case "wireless":
			return "J6120";
		case "automatización industrial":
			return "C3320";
		case "consultoría de ingeniería":
		case "delineantes":
		case "gestión de proyectos":
		case "matricería":
		case "peritaje judicial":
		case "proyectos":
			return "M7022";
		case "estudios de ingeniería":
			return "M7120";
		case "ingeniería de seguridad":
		case "ingeniería aeronáutica":
		case "ingeniería eléctrica":
		case "ingeniería energética":
		case "ingeniería industrial":
		case "ingeniería informática":
		case "ingeniería obra pública":
		case "ingeniería técnica":
			return "M71";
		case "ingeniería civil":
			return "F42";
		default:
			return null;
		}
	}

}
