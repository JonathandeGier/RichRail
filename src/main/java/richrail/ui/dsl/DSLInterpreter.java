package richrail.ui.dsl;

import parser.RichRailBaseListener;
import parser.RichRailParser;
import parser.RichRailParser.NewtraincommandContext;
import richrail.service.TreinService;

public class DSLInterpreter extends RichRailBaseListener {
    private TreinService service;
	
	public DSLInterpreter(TreinService s) {service = s;}
    
	@Override
	public void exitNewtraincommand(RichRailParser.NewtraincommandContext ctx) {
		service.newTrein(ctx.ID().getText());
		System.out.println("nieuwe trein:" + ctx.ID().getText());
	}

	@Override
	public void exitNewwagoncommand(RichRailParser.NewwagoncommandContext ctx) {
		String type = ctx.componenttype().getText();
		String name = ctx.ID().getText();
		String weight = "";
		String special = "";

	//	if(ctx.weight().NUMBER().getText() != null) {
	//		weight = ctx.weight().NUMBER().getText();
	//	}
	//
	//	if(ctx.specialvalue().NUMBER().getText() != null) {
	//		weight = ctx.specialvalue().NUMBER().getText();
	//	}

		System.out.println(type + " " + name + " weight:" + weight + " special:" + special);
	}
}
