package apacheThriftSrvB;


import java.util.ArrayList;
import java.util.List;

public enum PrendaDao{
	instance;

	private List<PrendaNoThrift> prendas;

	private PrendaDao() {
		String parrafada = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec et lacus a nisl "
				+ "efficitur elementum. Duis congue vehicula cursus. Cras egestas consectetur ante non varius. "
				+ "Aenean sed justo sed ipsum egestas dictum. Vivamus ac risus nec metus accumsan lacinia. Donec "
				+ "maximus nisi nibh. Aenean sed ultrices ante, eget consequat eros. Aenean id mattis augue. Integer "
				+ "volutpat accumsan dui iaculis tristique. Pellentesque egestas nisi eget urna placerat, vitae "
				+ "molestie justo egestas. Nulla ac dolor suscipit, tempor eros id, consequat massa.\n\nSed a dui "
				+ "mollis, sollicitudin lorem quis, bibendum tortor. Phasellus vel iaculis ipsum. Vestibulum auctor, "
				+ "eros eu hendrerit dignissim, nunc sapien pretium turpis, eget scelerisque leo augue vitae nisi. "
				+ "Pellentesque auctor posuere est, vitae eleifend eros cursus ut. Suspendisse sit amet neque "
				+ "euismod, posuere est eu, pretium lorem. Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
				+ "Praesent quis ex massa. Maecenas in efficitur magna. Etiam ac blandit sem, eu tincidunt nibh. "
				+ "Duis pretium, nisl volutpat condimentum aliquet, lacus justo aliquam urna, vitae vehicula velit "
				+ "arcu et libero. Etiam pellentesque sem nulla. Pellentesque id magna eros. Praesent ut metus "
				+ "sapien.\n\nDuis vel dignissim eros, non mattis enim. Donec eget sagittis orci. Etiam ut leo "
				+ "iaculis, pulvinar justo ut, accumsan lorem. Nunc neque diam, vestibulum vitae lobortis id, "
				+ "condimentum quis quam. Sed pharetra nulla at auctor feugiat. Nullam nec dictum sem. "
				+ "Curabitur fringilla rhoncus ullamcorper. Nunc at nibh bibendum, vulputate arcu sed, accumsan "
				+ "purus. Praesent sed purus vitae tellus efficitur sodales sit amet in enim. Aenean eleifend "
				+ "vestibulum velit, eu venenatis odio. Sed vel quam orci. Integer blandit nec elit et accumsan. "
				+ "Quisque facilisis vestibulum gravida. In a velit metus.\n\nDuis mollis, ex ornare gravida "
				+ "accumsan, purus tellus vulputate erat, non aliquam nunc augue id arcu. Donec a turpis eros. "
				+ "Ut condimentum feugiat ante ac fringilla. Cras quis quam et tellus sollicitudin vulputate nec "
				+ "at lectus. Etiam sagittis venenatis quam, et dapibus nisl suscipit id. Vestibulum eleifend, "
				+ "nisi sit amet lobortis accumsan, dolor ex semper augue, id posuere erat libero eget est. "
				+ "Aliquam facilisis eget velit eget consequat. Phasellus est mauris, tempor a risus sed, imperdiet "
				+ "eleifend felis. Fusce finibus tempor nibh non dignissim. Vestibulum aliquam aliquam lorem nec "
				+ "sollicitudin. Donec nulla felis, efficitur vel ultricies et, tempor vel diam. Vestibulum sit "
				+ "amet neque sagittis, porta justo a, vulputate turpis. Donec dignissim dignissim ex, et sagittis "
				+ "tortor pulvinar in. Ut volutpat leo ligula, vitae ultrices arcu egestas quis. Morbi tempor nisi"
				+ " et auctor feugiat.\n\nDonec feugiat ut nisi sit amet faucibus. Donec posuere turpis eget velit "
				+ "dignissim lacinia. Mauris auctor lectus maximus ante euismod malesuada. Nam consectetur, nulla "
				+ "at auctor pharetra, sapien erat finibus lectus, a vehicula lorem arcu eget tortor. Class aptent "
				+ "taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Sed in arcu non "
				+ "eros eu hendrerit dignissim, nunc sapien pretium turpis, eget scelerisque leo augue vitae nisi. "
				+ "Pellentesque auctor posuere est, vitae eleifend eros cursus ut. Suspendisse sit amet neque "
				+ "euismod, posuere est eu, pretium lorem. Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
				+ "Praesent quis ex massa. Maecenas in efficitur magna. Etiam ac blandit sem, eu tincidunt nibh. "
				+ "Duis pretium, nisl volutpat condimentum aliquet, lacus justo aliquam urna, vitae vehicula velit "
				+ "arcu et libero. Etiam pellentesque sem nulla. Pellentesque id magna eros. Praesent ut metus "
				+ "sapien.\n\nDuis vel dignissim eros, non mattis enim. Donec eget sagittis orci. Etiam ut leo "
				+ "iaculis, pulvinar justo ut, accumsan lorem. Nunc neque diam, vestibulum vitae lobortis id, "
				+ "condimentum quis quam. Sed pharetra nulla at auctor feugiat. Nullam nec dictum sem. "
				+ "Curabitur fringilla rhoncus ullamcorper. Nunc at nibh bibendum, vulputate arcu sed, accumsan "
				+ "purus. Praesent sed purus vitae tellus efficitur sodales sit amet in enim. Aenean eleifend "
				+ "vestibulum velit, eu venenatis odio. Sed vel quam orci. Integer blandit nec elit et accumsan. "
				+ "Quisque facilisis vestibulum gravida. In a velit metus.\n\nDuis mollis, ex ornare gravida "
				+ "accumsan, purus tellus vulputate erat, non aliquam nunc augue id arcu. Donec a turpis eros. "
				+ "Ut condimentum feugiat ante ac fringilla. Cras quis quam et tellus sollicitudin vulputate nec "
				+ "at lectus. Etiam sagittis venenatis quam, et dapibus nisl suscipit id. Vestibulum eleifend, "
				+ "nisi sit amet lobortis accumsan, dolor ex semper augue, id posuere erat libero eget est. "
				+ "Aliquam facilisis eget velit eget consequat. Phasellus est mauris, tempor a risus sed, imperdiet "
				+ "eleifend felis. Fusce finibus tempor nibh non dignissim. Vestibulum aliquam aliquam lorem nec "
				+ "sollicitudin. Donec nulla felis, efficitur vel ultricies et, tempor vel diam. Vestibulum sit "
				+ "amet neque sagittis, porta justo a, vulputate turpis. Donec dignissim dignissim ex, et sagittis "
				+ "tortor pulvinar in. Ut volutpat leo ligula, vitae ultrices arcu egestas quis. Morbi tempor nisi"
				+ " et auctor feugiat.\n\nDonec feugiat ut nisi sit amet faucibus. Donec posuere turpis eget velit "
				+ "dignissim lacinia. Mauris auctor lectus maximus ante euismod malesuada. Nam consectetur, nulla "
				+ "at auctor pharetra, sapien erat finibus lectus, a vehicula lorem arcu eget tortor. Class aptent "
				+ "taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Sed in arcu non "
				+ "eros eu hendrerit dignissim, nunc sapien pretium turpis, eget scelerisque leo augue vitae nisi. "
				+ "Pellentesque auctor posuere est, vitae eleifend eros cursus ut. Suspendisse sit amet neque "
				+ "euismod, posuere est eu, pretium lorem. Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
				+ "Praesent quis ex massa. Maecenas in efficitur magna. Etiam ac blandit sem, eu tincidunt nibh. "
				+ "Duis pretium, nisl volutpat condimentum aliquet, lacus justo aliquam urna, vitae vehicula velit "
				+ "arcu et libero. Etiam pellentesque sem nulla. Pellentesque id magna eros. Praesent ut metus "
				+ "sapien.\n\nDuis vel dignissim eros, non mattis enim. Donec eget sagittis orci. Etiam ut leo "
				+ "iaculis, pulvinar justo ut, accumsan lorem. Nunc neque diam, vestibulum vitae lobortis id, "
				+ "condimentum quis quam. Sed pharetra nulla at auctor feugiat. Nullam nec dictum sem. "
				+ "Curabitur fringilla rhoncus ullamcorper. Nunc at nibh bibendum, vulputate arcu sed, accumsan "
				+ "purus. Praesent sed purus vitae tellus efficitur sodales sit amet in enim. Aenean eleifend "
				+ "vestibulum velit, eu venenatis odio. Sed vel quam orci. Integer blandit nec elit et accumsan. "
				+ "Quisque facilisis vestibulum gravida. In a velit metus.\n\nDuis mollis, ex ornare gravida "
				+ "accumsan, purus tellus vulputate erat, non aliquam nunc augue id arcu. Donec a turpis eros. "
				+ "Ut condimentum feugiat ante ac fringilla. Cras quis quam et tellus sollicitudin vulputate nec "
				+ "at lectus. Etiam sagittis venenatis quam, et dapibus nisl suscipit id. Vestibulum eleifend, "
				+ "nisi sit amet lobortis accumsan, dolor ex semper augue, id posuere erat libero eget est. "
				+ "Aliquam facilisis eget velit eget consequat. Phasellus est mauris, tempor a risus sed, imperdiet "
				+ "eleifend felis. Fusce finibus tempor nibh non dignissim. Vestibulum aliquam aliquam lorem nec "
				+ "sollicitudin. Donec nulla felis, efficitur vel ultricies et, tempor vel diam. Vestibulum sit "
				+ "amet neque sagittis, porta justo a, vulputate turpis. Donec dignissim dignissim ex, et sagittis "
				+ "tortor pulvinar in. Ut volutpat leo ligula, vitae ultrices arcu egestas quis. Morbi tempor nisi"
				+ " et auctor feugiat.\n\nDonec feugiat ut nisi sit amet faucibus. Donec posuere turpis eget velit "
				+ "dignissim lacinia. Mauris auctor lectus maximus ante euismod malesuada. Nam consectetur, nulla "
				+ "at auctor pharetra, sapien erat finibus lectus, a vehicula lorem arcu eget tortor. Class aptent "
				+ "taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Sed in arcu non "
				+ "magna aliquam pretium nec sit amet diam. Maecenas ligula ex, egestas id posuere et, blandit sit "
				+ "amet mauris.";
		
		this.prendas = new ArrayList<PrendaNoThrift>();
		PrendaNoThrift p = new PrendaNoThrift("Prenda1", TipoNoThrift.CAMISA, "40", "Azul", parrafada, "0");
		this.prendas.add(p);

		for(int i =0; i<30 ;i++){
			p = new PrendaNoThrift("Prenda1", TipoNoThrift.CAMISA, String.valueOf(i), "Verde", parrafada, "0");
			this.prendas.add(p);
		}
		
		
		p = new PrendaNoThrift("Prenda1", TipoNoThrift.CAMISA, "40", "Verde", parrafada, "0");
		this.prendas.add(p);
		p = new PrendaNoThrift("Prenda1", TipoNoThrift.CAMISA, "38", "Azul", parrafada, "0");
		this.prendas.add(p);
		p = new PrendaNoThrift("Prenda2", TipoNoThrift.CAMISA, "36", "Rojo", parrafada, "0");
		this.prendas.add(p);
		p = new PrendaNoThrift("Prenda2", TipoNoThrift.CAMISA, "38", "Rojo", parrafada, "0");
		this.prendas.add(p);
		p = new PrendaNoThrift("Prenda2", TipoNoThrift.CAMISA, "40", "Rojo", parrafada, "0");
		this.prendas.add(p);
		p = new PrendaNoThrift("Prenda3", TipoNoThrift.PANTALON, "42", "Verde", parrafada, "0");
		this.prendas.add(p);
		p = new PrendaNoThrift("Prenda3", TipoNoThrift.PANTALON, "42", "Blanco", parrafada, "0");
		this.prendas.add(p);
		p = new PrendaNoThrift("Prenda4", TipoNoThrift.ZAPATO, "40", "Azul", parrafada, "0");
		this.prendas.add(p);
		p = new PrendaNoThrift("Prenda5", TipoNoThrift.ZAPATO, "38", "Negro", parrafada, "0");
		this.prendas.add(p);
		p = new PrendaNoThrift("Prenda5", TipoNoThrift.ZAPATO, "40", "Rojo", parrafada, "0");
		this.prendas.add(p);
		p = new PrendaNoThrift("Prenda5", TipoNoThrift.ZAPATO, "36", "Negro", parrafada, "0");
		this.prendas.add(p);
		p = new PrendaNoThrift("Prenda5", TipoNoThrift.ZAPATO, "38", "Rojo", parrafada, "0");
		this.prendas.add(p);
	}

	public List<PrendaNoThrift> getPrendas() {
		return prendas;
	}

}