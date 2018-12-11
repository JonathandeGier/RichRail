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
		int weight = 0;
		int special = 0;

		if(ctx.weight() != null) {
			weight = Integer.parseInt(ctx.weight().NUMBER().getText());
		}

		if(ctx.specialvalue() != null) {
			special = Integer.parseInt(ctx.specialvalue().NUMBER().getText());
		}

		if(weight == 0 && special == 0) {
			service.createRollingComponent(name, type);
		} else if(weight == 0 && special != 0) {
			service.createRollingComponent(name, type, special);
		} else if(weight != 0 && special == 0) {
			service.createRollingComponent(name, weight, type);
		} else {
			service.createRollingComponent(name, weight, type, special);
		}

		System.out.println(type + " " + name + " weight:" + weight + " special:" + special);
	}

	@Override
	public void exitAddcommand(RichRailParser.AddcommandContext ctx) {
		String wagon = ctx.ID(0).getText();
		String train = ctx.ID(1).getText();
		service.addComponentToTrain(train, wagon);
		System.out.println("voeg " + wagon + " aan " + train + " toe");
	}

	@Override
	public void exitGetcommand(RichRailParser.GetcommandContext ctx) {
		String type = ctx.type().getText();
		String name = ctx.ID().getText();

		if(type.equals("wagon")) {
			System.out.println(service.getNumTrainSeats(name));
		} else if (type.equals("train")) {
			System.out.println(service.getNumWagonSeats(name));
		}
	}

	@Override
	public void exitDelcommand(RichRailParser.DelcommandContext ctx) {
		String type = ctx.type().getText();
		String name = ctx.ID().getText();

		if(type.equals("wagon")) {
			service.removeComponent(name);
		} else if (type.equals("train")) {
			service.removeTrain(name);
		}
	}

	@Override
	public void exitRemcommand(RichRailParser.RemcommandContext ctx) {
		String wagon = ctx.ID(0).getText();
		String train = ctx.ID(1).getText();
		service.removeComponentFromTrain(train, wagon);
		System.out.println("verwijder " + wagon + " van " + train);
	}
}
