package java_ui.graphs.alternatives;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse.Mode;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.decorators.EllipseVertexShapeTransformer;
import java_ui.graphs.alternatives.lattice.JungLatticeLayout;
import java_ui.graphs.alternatives.lattice.Utils;

public class AlternativesGraphPanel extends JPanel {
	
	private AlternativesGraph graph;
	private JungLatticeLayout<AlternativesGraphVertex, AlternativesGraphEdge> layout;
	private VisualizationViewer<AlternativesGraphVertex, AlternativesGraphEdge> vv;
	private DefaultModalGraphMouse<AlternativesGraphVertex, AlternativesGraphEdge> mouse;
	
	public AlternativesGraphPanel(){
		this.graph = new AlternativesGraph();
		setLayout(new BorderLayout());
		
		this.mouse = new DefaultModalGraphMouse<AlternativesGraphVertex, AlternativesGraphEdge>();
		
	}
	
	
	private void initVisualizerViewer(){
		this.vv = new VisualizationViewer<AlternativesGraphVertex, AlternativesGraphEdge>(this.layout);
		
		this.vv.setGraphMouse(this.mouse);	
		
		this.mouse.add(new AlternativesGraphPopupMenuMousePlugin(this.graph, this.vv, this.layout));
		
		JMenu menu = this.mouse.getModeMenu();
		menu.setText("MOUSE MODE");
		menu.setPreferredSize(new Dimension(200, 20));
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(menu);
		
		this.add(menuBar, BorderLayout.NORTH);
		
		this.mouse.setMode(Mode.PICKING);
		
		this.vv.getRenderContext().setVertexFillPaintTransformer(new Transformer<AlternativesGraphVertex, Paint>() {

			@Override
			public Paint transform(AlternativesGraphVertex v) {
				if(vv.getPickedVertexState().isPicked(v)){
					return Color.CYAN;
				}
				else{
					if(v.isSelected()){
						return Color.WHITE;
					}
					else{
						return new Color(120,120,120);
					}
				}
				
			}
		});
		
		
		this.vv.getRenderContext().setEdgeLabelTransformer(new Transformer<AlternativesGraphEdge, String>(){
				
			@Override
			public String transform(AlternativesGraphEdge edge){
				return edge.toString();
			}
			
		});
		
		this.vv.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line<AlternativesGraphVertex,AlternativesGraphEdge>());
	
	
		this.vv.getRenderContext().setVertexLabelTransformer(new Transformer<AlternativesGraphVertex, String>(){

			@Override
			public String transform(AlternativesGraphVertex vertex) {
				return vertex.getId();
			}
			
		});
		
		this.vv.getRenderContext().setVertexShapeTransformer(new AlternativesGraphVertexShapeTransformer());
		this.vv.getRenderContext().setVertexFontTransformer(new AlternativesGraphVertexFontTransformer());
		this.vv.getRenderContext().setEdgeFontTransformer(new AlternativesGraphEdgeFontTransformer());
		
		this.vv.getRenderContext().setVertexStrokeTransformer(new Transformer<AlternativesGraphVertex, Stroke>() {			
			
			@Override
			public Stroke transform(AlternativesGraphVertex v) {
				float [] dash = {5.0f};
				Stroke toReturn = new BasicStroke();
				
				if(v instanceof AlternativesGraphCompoundVertex)
					toReturn = new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
				
				return  toReturn;
			}
			
		});
		
		this.vv.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				super.componentResized(arg0);
				Utils.fitGraph(vv, layout);
			}});
	}
	
	public void loadGraph(){
		this.graph.load();
		
		this.layout = new  JungLatticeLayout<AlternativesGraphVertex, AlternativesGraphEdge>(this.graph.getGraph());
		
		if(this.vv != null){
			this.remove(this.vv);
		}
			
		initVisualizerViewer();
		
		this.add(this.vv, BorderLayout.CENTER);
	}
	
	public void clearGraph(){
		if(this.graph != null) {
			this.graph.clearGraph();
			if(this.layout != null) {
				this.layout.setGraph(this.graph.getGraph());
				if(this.vv != null) {
					vv.setGraphLayout(layout);
				}
			}
		}
		
	}
	
	private class AlternativesGraphVertexShapeTransformer extends EllipseVertexShapeTransformer<AlternativesGraphVertex>{
		
		AlternativesGraphVertexShapeTransformer() {
            setSizeTransformer(new Transformer<AlternativesGraphVertex, Integer>() {
            	
            	public Integer transform(AlternativesGraphVertex v){
            		return v instanceof AlternativesGraphCompoundVertex ? 40 : 20;
            	}
			});
        }
	}
	
	private class AlternativesGraphVertexFontTransformer implements Transformer<AlternativesGraphVertex, Font>{
		
		private Font font = new Font("Calibri", Font.BOLD, 16);
		
		@Override
		public Font transform(AlternativesGraphVertex vertex) {
            
            return font;
        }
		
	}
	
	private class AlternativesGraphEdgeFontTransformer implements Transformer<AlternativesGraphEdge, Font>{
		
		private Font font = new Font("Calibri", Font.BOLD, 16);
		
		@Override
		public Font transform(AlternativesGraphEdge edge) {
            return font;
        }
		
	}
}
