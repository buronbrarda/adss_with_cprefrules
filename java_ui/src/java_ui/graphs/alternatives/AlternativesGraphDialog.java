package java_ui.graphs.alternatives;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JPanel;

public class AlternativesGraphDialog extends JDialog {
	
	private Dimension defaultDimension = new Dimension(500,500);
	
	private AlternativesGraphPanel graphPanel;
	
	
	public AlternativesGraphDialog(){
		
		this.setTitle("Explanation graph - (Prototype Version)");
		this.setModalityType(ModalityType.MODELESS);
		
		this.setSize(defaultDimension);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel graphContainer = new JPanel();
		
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(graphContainer, BorderLayout.CENTER);
		graphContainer.setLayout(new BorderLayout(0, 0));
		
		graphPanel = new AlternativesGraphPanel();
		graphContainer.add(graphPanel);
		
		graphPanel.loadGraph();
	}
}
