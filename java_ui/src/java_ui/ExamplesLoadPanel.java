package java_ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JSeparator;
import javax.swing.table.TableModel;

import java_ui.steps.DefineCprefRulesStepPanel;
import java_ui.steps.DefineEvidenceStepPanel;
import java_ui.steps.DefineRuleImportanceStepPanel;
import java_ui.steps.DefineCriteriaStepPanel;
import java_ui.steps.RunStepPanel;
import java_ui.table_editor.model_builder.CriteriaTableModelBuilder;
import java_ui.table_editor.model_builder.EvidenceTableModelBuilder;
import java_ui.table_editor.model_builder.ImportanceTableModelBuilder;
import java_ui.table_editor.model_builder.RulesTableModelBuilder;
import java_ui.table_editor.table_reader.CSVTableReader;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

public class ExamplesLoadPanel extends JPanel {
	
	
	private DefineCriteriaStepPanel criteriaStep;
	private DefineEvidenceStepPanel evidenceStep;
	private DefineCprefRulesStepPanel rulesStep;
	private DefineRuleImportanceStepPanel importanceStep;
	private RunStepPanel runStep;


	public ExamplesLoadPanel(DefineCriteriaStepPanel criteriaStep, DefineEvidenceStepPanel evidenceStep, DefineCprefRulesStepPanel rulesStep, DefineRuleImportanceStepPanel importanceStep,RunStepPanel runStep) {
		this.criteriaStep = criteriaStep;
		this.evidenceStep = evidenceStep;
		this.rulesStep = rulesStep;
		this.importanceStep = importanceStep;
		this.runStep = runStep;
		
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel container = new JPanel();
		GridBagLayout gbc_container = new GridBagLayout();
		gbc_container.columnWidths = new int[]{0, 66, 118, 95, 0};
		gbc_container.rowHeights = new int[]{37, 39, 0, 0};
		gbc_container.columnWeights = new double[]{};
		gbc_container.rowWeights = new double[]{};
		add(container, BorderLayout.CENTER);
		GridBagLayout gbl_container = new GridBagLayout();
		gbl_container.columnWidths = new int[]{45, 50, 50, 50, 0};
		gbl_container.rowHeights = new int[]{70, 0};
		gbl_container.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_container.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		container.setLayout(gbl_container);
		
		
		
		JLabel instructionLabel = new JLabel("Click on any button to load an example.");
		GridBagConstraints gbc_instructionLabel = new GridBagConstraints();
		gbc_instructionLabel.gridy = 0;
		gbc_instructionLabel.insets = new Insets(5, 5, 0, 5);
		gbc_instructionLabel.anchor = GridBagConstraints.WEST;
		gbc_instructionLabel.gridx = 0;
		container.add(instructionLabel, gbc_instructionLabel);
		
		JButton loadButton1 = new JButton("1");
		loadButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					loadExample(1);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JButton loadButton2 = new JButton("2");
		loadButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					loadExample(2);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		JButton loadButton3 = new JButton("3");
		loadButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					loadExample(3);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JButton loadButton4 = new JButton("4");
		loadButton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					loadExample(4);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JButton loadButton5 = new JButton("5");
		loadButton5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					loadExample(5);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 0;
		container.add(loadButton1, gbc_btnNewButton);
		
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 0;
		container.add(loadButton2, gbc_btnNewButton_1);
		
		GridBagConstraints gbc_loadButton = new GridBagConstraints();
		gbc_loadButton.insets = new Insets(0, 0, 0, 5);
		gbc_loadButton.gridx = 3;
		gbc_loadButton.gridy = 0;
		container.add(loadButton3, gbc_loadButton);
		
		GridBagConstraints gbc_btn4 = new GridBagConstraints();
		gbc_btn4.insets = new Insets(0, 0, 0, 5);
		gbc_btn4.gridx = 4;
		gbc_btn4.gridy = 0;
		container.add(loadButton4, gbc_btn4);
		
		GridBagConstraints gbc_btn5 = new GridBagConstraints();
		gbc_btn5.insets = new Insets(5, 0, 5, 5);
		gbc_btn5.gridx = 5;
		gbc_btn5.gridy = 0;
		container.add(loadButton5, gbc_btn5);
		
		JSeparator separator = new JSeparator();
		add(separator, BorderLayout.SOUTH);
	
	}
	
	
	private void loadExample(int number) throws IOException{
			
		
			String criteria_example_path = "criteria_example_"+number+".csv";
			String evidence_example_path = "evidence_example_"+number+".csv";
			String cpref_rules_example_path = "cpref_rules_example_"+number+".csv";
			String importance_example_path = "importance_orders_"+number+".csv";
			String agents_priority_example_path = "agents_priority_order_example_"+number+".csv";
			
			
			File criteria_file = new File(DSJavaUI.getExamplesFolderRelativePath()+"/examples/"+criteria_example_path);
			File evidence_file = new File(DSJavaUI.getExamplesFolderRelativePath()+"/examples/"+evidence_example_path);
			File cpref_rules_file = new File(DSJavaUI.getExamplesFolderRelativePath()+"/examples/"+cpref_rules_example_path);
			File importance_file = new File(DSJavaUI.getExamplesFolderRelativePath()+"/examples/"+importance_example_path);
			File agents_priority_file = new File(DSJavaUI.getExamplesFolderRelativePath()+"/examples/"+agents_priority_example_path);
			
			
			TableModel criteriaModel = new CriteriaTableModelBuilder(new CSVTableReader(criteria_file)).getTableModel();
			TableModel evidenceModel = new EvidenceTableModelBuilder(new CSVTableReader(evidence_file)).getTableModel();
			TableModel cprefRulesModel = new RulesTableModelBuilder(new CSVTableReader(cpref_rules_file)).getTableModel();
			TableModel importanceModel = new ImportanceTableModelBuilder(new CSVTableReader(importance_file)).getTableModel();
			
			criteriaStep.setTableModel(criteriaModel);
			evidenceStep.setTableModel(evidenceModel);
			rulesStep.setTableModel(cprefRulesModel);
			
			importanceStep.setTableModel(importanceModel);
			importanceStep.setAgentsPriorityOrder(agents_priority_file);
			
			runStep.enableStep();
			
		}

}
