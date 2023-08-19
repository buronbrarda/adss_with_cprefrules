package java_ui.steps;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jpl7.Compound;
import org.jpl7.PrologException;
import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Util;

import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.awt.event.ActionEvent;

public class DefineAgentPriorityOrderDialog extends JDialog {
	
	private JTextArea textArea;
	
	private final JPanel contentPanel = new JPanel();
	private DefineAgentPriorityOrderDialog me;

	/**
	 * Create the dialog.
	 */
	public DefineAgentPriorityOrderDialog(final DefineRuleImportanceStepPanel step) {
		setTitle("Agents priority-order");
		setBounds(100, 100, 455, 122);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		me = this;
		
		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setTabSize(4);
		textArea.setLineWrap(true);
		textArea.setText(getCurrentAgentsPriorityOrder());
		contentPanel.add(textArea, BorderLayout.CENTER);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("Save");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					String input = textArea.getText();
					input.replaceAll("\\s", "");
					String [] statements = input.split(";");
					
					Query cleaning_query = new Query("remove_priorities");
					if (cleaning_query.hasNext()) {cleaning_query.next();}
					
					if (statements.length > 0) {
					
						for (String s : statements) {
							String [] splited = s.split(">");
							Compound comp = new Compound(">", new Term[] {Util.textToTerm(splited[0]), Util.textToTerm(splited[1])} );
							
							Query q = new Query("add_priority", new Term [] {comp});
							
							if(q.hasNext()){
								q.next();
							}
						}
					
					}
					
					
				}catch(PrologException e){
					JOptionPane.showMessageDialog(null, "You have not specified a correct order", "Error", JOptionPane.ERROR_MESSAGE);
					step.getFollowingStep().disableStep();
					e.printStackTrace();
				}
				
				me.setVisible(false);
				
			}
		});
		okButton.setActionCommand("Save");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
	}
	
	private String getCurrentAgentsPriorityOrder() {
		
		Query q = new Query("has_priority(AgentA,AgentB)");
		
		ArrayList<String> statements = new ArrayList<String>();
		for (Map<String, Term> solution : q) {
			statements.add(solution.get("AgentA").toString() + " > "+solution.get("AgentB").toString());			
		}
		
		String toReturn = "";
		for(String s : statements){
			toReturn = toReturn + s + "; ";
		}

		return toReturn.substring(0,Math.max(0, toReturn.length()-2));
	}

}
