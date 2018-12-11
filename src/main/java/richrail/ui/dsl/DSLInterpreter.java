package richrail.ui.dsl;

import parser.RichRailBaseListener;
import parser.RichRailParser.NewtraincommandContext;
import richrail.service.TreinService;

public class DSLInterpeter extends RichRailBaseListener {
    private TreinService service;
	
	public DSLInterpeter(TreinService s) {service = s;}
    
	@Override
	public void exitNewtraincommand(NewtraincommandContext ctx) {
	//	service.newTrein(ctx.ID().getText());
		System.out.println(ctx.ID().getText());
		System.out.println("nieuwe trein");
	}


}
