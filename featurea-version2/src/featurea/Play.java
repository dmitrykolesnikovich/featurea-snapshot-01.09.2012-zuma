package featurea;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Play extends Screen{
	
	// public:
    public boolean alive = true;
   	public Vector3 camera = new Vector3(0,0,0);
	// not public:
   	private boolean old;   
    private Map<String, List<Object[]>> id2figures = new HashMap<String, List<Object[]>>();    
	/*package*/ Feature[] stack;	
	/*package*/ Map<Object[], Set<Object>> areas = new HashMap<Object[], Set<Object>>(); 
    
	public Play(Feature[] stack){
		this.stack = stack;		
        for (int i = 0; i < this.stack.length; i++){
            init1FeatureFigures(this.stack[i]);
            init1FigureObjects(this.stack[i]);
        }  
	}

    /** Init on Activity started. */
    private void init1FigureObjects(Feature feature) {
    	for (Object[] figure : feature.figures){
    		if (figure==null){ // For 'ProGuard' obfuscator.
                    System.out.println("Figures.init1FigureObjects: figure==null");    			
    		}else{
                    String id = figure.getClass().getCanonicalName();   
                    id = id.substring(0, id.length()-2);
                    List<Object[]> figures = id2figures.get(id);
                    if (figures == null){
                        figures = new ArrayList<Object[]>();
                    }
                    figures.add(figure);
                    id2figures.put(id, figures);
                    areas.put(figure, new HashSet<Object>());
                    old = true;
    		}            
        }				
    }
    
   
    private void init1FeatureFigures (Feature instance){
            Field[] clusterJavaField = instance.getClass().getDeclaredFields();
            Object[][] featureGroups = new Object[clusterJavaField.length][];	
            for (int i=0; i<clusterJavaField.length; i++){
                Field groupJavaField = clusterJavaField[i];
                try {   // no field.setAccessible(true); because every 'figure' field MUST be public
                		groupJavaField.setAccessible(true); // Specialy for 'ProGuard' obfuscator.
                		Object obj = groupJavaField.get(instance);
                        featureGroups[i] = (Object[])obj;
                } catch (Exception e) {	
                        e.printStackTrace();
                } 		
            }
            instance.figures = featureGroups;           
    }	
    
    void updateFamily() {
    	if (old){
    		for (Feature feature : this.stack){
                updateFamily(feature, this.areas);
            }
            old = false;
    	}        
    }      
    
   

	public void update() {		
		if (alive){	
	        FeatureEngine.update(this);   
		} 		        
    }
    
	@Override
    public boolean add(Animation animation){
		boolean result = super.add(animation);
		if (result){
			add((Object)animation);
		}    	
    	return result;
    }
	
	public boolean remove(Animation animation){    	
		boolean result = super.remove(animation);
		if (result){
			remove((Object)animation);
		}	   	
	   	return result;
    }


 	public void add(Object area){
        String objectid = area.getClass().getCanonicalName();
        List<Object[]> figures = id2figures.get(objectid);
        if (figures != null){
            for (Object[] figure : figures){
                Set<Object> set = areas.get(figure);
                if (set == null){
                    set = new HashSet<Object>();
                }
                set.add(area);
                areas.put(figure, set);
                old = true;
            }		
        }

    }
 	
 	@Override
 	public boolean remove(Object area) {
        for (Set<Object> figureobjectsvalues : areas.values()){
            figureobjectsvalues.remove(area);
            this.old = true;
        }       
        return false;
    }		
    
 	@Override
    public void clear() {
    	super.clear();
    	areas.clear();
    	this.old = true;
    }
    
    // About family:
    private List<Object[]>[] areaGroups;    
    private Object[] currentFigureToAdd;    
    private void updateFamily(Feature feature, Map<Object[], Set<Object>> figureAreas) {
    	areaGroups = new List[feature.figures.length];	
        feature.family = new ArrayAreas();
        for (int j = 0; j < feature.figures.length; j++){ // *
            Object[] figure = feature.figures[j];		
            if (figure==null){
                System.out.println("Figures.updateFamily: figure==null");            	
            }else{
            	Object[] figureObjects = toArray(figureAreas.get(figure));               
                areaGroups[j] = new ArrayList<Object[]>();
                currentFigureToAdd = null;
                step1CreateClusterFromGroupsFromFigureAreas(areaGroups[j], figure, figureObjects, 0, 0);	
            }
            				
        }				
        step2CreateFamilyForCurrentFeature(0, new Object[]{}, feature);		       	
    }

    // Init areas
    private void step1CreateClusterFromGroupsFromFigureAreas(List<Object[]> groups, Object[] group, Object[] groupSubjects, final int count, final int cur){
        if (currentFigureToAdd == null){
                currentFigureToAdd = new Object[group.length];
        }
        if (count < group.length){
            for (int i = cur; i < groupSubjects.length + 1 - group.length + count; i++){
                currentFigureToAdd[count] = groupSubjects[i];
                if (count == group.length-1){
                        addGroupToCluster(groups, currentFigureToAdd);
                }				
                step1CreateClusterFromGroupsFromFigureAreas(groups, group, groupSubjects, count+1, i+1);
            }
        }					
    }	

    private void step2CreateFamilyForCurrentFeature(int count, Object[] currentGroup, final Feature feature){
        final int clusterCount = areaGroups.length;
        if(count < clusterCount){	    	
            List<Object[]> cluster = areaGroups[count];
            for (Object[] groupToAdd : cluster){       		
                Object[] group = returnClusterToComplete(groupToAdd, currentGroup);
                if (count == clusterCount - 1){ 
                    feature.family.add(group);
                }
                step2CreateFamilyForCurrentFeature(count+1, group, feature);
            }	    		                         
        }  			     
    }	    

    private void addGroupToCluster(List<Object[]> cluster, Object[] group){
        Object[] clonedGroup = cloneGroup(group);
        cluster.add(clonedGroup);
    }

    // Util methods:
    private Object[] cloneGroup (Object[] group){
        Object[] clonedGroup = new Object[group.length];
        for (int i=0; i<group.length; i++){
            clonedGroup[i] = group[i];
        }
        return clonedGroup;
    }

    private Object[] toArray(Set<Object> areas) {		
        Object[] array = new Object[areas.size()];							
        int count = 0;							
        for (Object area : areas){
            array[count] = area;
            count++;
        }	
        return array;
    }    
    private Object[] returnClusterToComplete(Object[] groupToAdd, Object[] clusterToCreate){
        Object[] tempCluster = new Object[groupToAdd.length + clusterToCreate.length];
        int n = 0;
        for(int i = 0; i < clusterToCreate.length; i++){
            tempCluster[n] = clusterToCreate[i];
            n++;
        } 
        for(int i = 0; i < groupToAdd.length; i++){
            tempCluster[n] = groupToAdd[i];
            n++;
        }
        return tempCluster; 
    }
    
    
}