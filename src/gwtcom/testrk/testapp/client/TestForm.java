package gwtcom.testrk.testapp.client;

import com.google.gwt.core.client.EntryPoint;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.SpacerItem;
import com.smartgwt.client.widgets.form.fields.StaticTextItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.form.validator.MatchesFieldValidator;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class TestForm implements EntryPoint {
	
	private ListGrid valuesGrid;

	@Override
	public void onModuleLoad() {
		
		DynamicForm passwordForm = new DynamicForm();
		passwordForm.setTop(500);
		
		TextItem userName = new TextItem("username");
		PasswordItem password = new PasswordItem("password");
		PasswordItem confirm = new PasswordItem("confirm");
		confirm.setShouldSaveValue(false);
		confirm.setValidators(new MatchesFieldValidator() {{
			setOtherField("password");
			setErrorMessage("Password do not match");
		}} );
		
		ButtonItem showVals = new ButtonItem();
		showVals.setTitle("Show Vals");
		showVals.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (event.getForm().validate()) {
					SC.say(event.getForm().getValues().toString());
				}
			}		
		});
		
		passwordForm.setItems(userName, password, confirm, showVals);
		
		passwordForm.draw();
		
		DynamicForm testForm = new DynamicForm();
		testForm.setWidth(500);
		
		testForm.setNumCols(4);
		
		StaticTextItem info  = new StaticTextItem();
		info.setShowTitle(false);
		info.setValue("Can't edit this!");
		info.setShouldSaveValue(false);
		
		TextItem value1 = new TextItem("value1");
		
		ComboBoxItem value2 = new ComboBoxItem("value2");
		value2.setValueMap("Apple", "Orange", "strawberry", "blueberry");
		value2.setAddUnknownValues(false);
		
		TextItem value3 = new TextItem("value3");
		
		TextAreaItem largeItem = new TextAreaItem("value4");
		largeItem.setColSpan(3);
		largeItem.setWidth("*");
		
		ButtonItem showpasswordVals = new ButtonItem();
		showpasswordVals.setTitle("Show Values");
		showpasswordVals.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				valuesGrid.setData (new Record[] {
						event.getForm().getValuesAsRecord()
				});				
			}			
		});
		testForm.setItems(new SpacerItem(), info, value1, value2, value3, largeItem, showpasswordVals);		
		testForm.draw();
		
		valuesGrid = new ListGrid();
		valuesGrid.setFields(new ListGridField("value1"), new ListGridField("value2"), new ListGridField("value3"));
		
		valuesGrid.setTop(200);
		valuesGrid.draw();		
		
	}
}
