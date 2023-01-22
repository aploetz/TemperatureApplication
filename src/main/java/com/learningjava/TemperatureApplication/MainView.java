package com.learningjava.TemperatureApplication;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {

	private TemperatureController tempController;
	
	private TextField celsiusTxt = new TextField("Celsius");
	private TextField fahrenheitTxt = new TextField("Fahrenheit");

	public MainView() {

		this.tempController = new TemperatureController();
		add(getTextFields(), getButtons());
	}

	private Component getTextFields() {

		HorizontalLayout layout = new HorizontalLayout();
		layout.setAlignItems(Alignment.BASELINE);
		layout.add(celsiusTxt,fahrenheitTxt);

		return layout;
	}


	private Component getButtons() {
	
		HorizontalLayout layout = new HorizontalLayout();
			
		Button convertButton = new Button("Convert");
		convertButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		layout.add(convertButton);
	
		Button clearButton = new Button("Clear");
		clearButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		layout.add(clearButton);

		clearButton.addClickListener(click ->{
			celsiusTxt.clear();
			fahrenheitTxt.clear();
		});

		convertButton.addClickListener(click ->{
			if (celsiusTxt.isEmpty() && !fahrenheitTxt.isEmpty()) {
				// convert fahrenheit to celsius
				Double fahrenheitInput = Double.parseDouble(fahrenheitTxt.getValue());
				Double celsiusOutput = tempController.computeCelsius(fahrenheitInput);
				celsiusTxt.setValue(celsiusOutput.toString());			
			} else if (!celsiusTxt.isEmpty() && fahrenheitTxt.isEmpty()){
				// convert celsius to fahrenheit
				Double celsiusInput = Double.parseDouble(celsiusTxt.getValue());
				Double fahrenheitOutput = tempController.computeFahrenheit(celsiusInput);
				fahrenheitTxt.setValue(fahrenheitOutput.toString());
			}
		});

		return layout;		
	}

}
