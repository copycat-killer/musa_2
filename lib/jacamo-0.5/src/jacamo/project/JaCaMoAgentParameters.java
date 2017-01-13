package jacamo.project;

import jason.architecture.AgArch;
import jason.asSemantics.Agent;
import jason.asSyntax.ASSyntax;
import jason.asSyntax.Literal;
import jason.asSyntax.Term;
import jason.asSyntax.parser.ParseException;
import jason.bb.DefaultBeliefBase;
import jason.mas2j.AgentParameters;
import jason.mas2j.ClassParameters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class JaCaMoAgentParameters extends AgentParameters {
    
    protected Set<String>    wks   = new TreeSet<String>();
    protected List<String[]> roles = new ArrayList<String[]>(); // each [org,group,role]
    protected List<String[]> focus = new ArrayList<String[]>(); // each [artId,wskId,host]
    protected JaCaMoProject  project;
    
    public JaCaMoAgentParameters(JaCaMoProject project) {
        this.project = project;
    }
    
    public JaCaMoAgentParameters(JaCaMoProject project, AgentParameters a) {
        super(a);
        this.project = project;
    }

    public void addInitBel(Literal l) {
        String op = getOption("beliefs");
        if (op == null)
            op = "";
        else
            op += ",";
        op += l.toString();
        addOption("beliefs", op);
    }
    
    public void addInitGoal(Literal l) {
        String op = getOption("goals");
        if (op == null)
            op = "";
        else
            op += ",";
        op += l.toString();
        addOption("goals", op);
    }

    public AgentParameters copy() {
        JaCaMoAgentParameters newap = new JaCaMoAgentParameters(this.project);
        this.copyTo(newap);
        return newap;
    }
    
    protected void copyTo(JaCaMoAgentParameters newap) {
        super.copyTo(newap);
        newap.wks = new HashSet<String>(this.wks);
        newap.roles = new ArrayList<String[]>(this.roles);
        newap.focus = new ArrayList<String[]>(this.focus);
        newap.project = this.project;
    }
    
    public void addWorkspace(String w, String h) {
        wks.add(w);
        if (h != null) {
            project.setWorkspaceNode(w, h);
        }
    }
    public Collection<String> getWorkspaces() {
        List<String> wslocal = new ArrayList<String>();
        List<String> wsrem   = new ArrayList<String>();
        // add local workspace first
        for (String w: wks) {
            String n = project.getWorkspaceNode(w);
            if (n == null)
                wslocal.add(w);
            else if (project.getNodeHost(n) == null)
                wslocal.add(w);
            else
                wsrem.add(w);
        }
        wslocal.addAll(wsrem);
        return wslocal;
    }

    public void setProject(JaCaMoProject p) {
        this.project = p;
    }
    public JaCaMoProject getProject() {
        return project;
    }
    
    
    public void addRole(String org, String g, String r) {
        roles.add(new String[] { org, g, r });
    }   
    public void addRole(String g, String r) {
        int pdot = g.indexOf(".");
        if (pdot > 0) {
            String orgId = g.substring(0,pdot);
            g = g.substring(pdot+1);
            addRole(orgId, g, r);
        } else {
            addRole(null, g, r);
        }
    }
    
    public List<String[]> getRoles() {
        return roles;
    }
    
    public void addFocus(String artId, String w, String n) {
        if (w != null && !wks.contains(w))
            addWorkspace(w,n);
        focus.add(new String[] { artId, w } );
        
    }
    
    public void addFocus(String artId, String h) {
        int pdot = artId.indexOf(".");
        if (pdot > 0) {
            String workspaceId = artId.substring(0,pdot);
            artId = artId.substring(pdot+1);
            addFocus(artId,workspaceId, h);
        } else {
            addFocus(artId,null,h);
        }
    }

    public void fix() {
        for (String[] f: focus) {
            if (f[1] == null) {
                JaCaMoWorkspaceParameters w = project.getArtifactWorkspace(f[0]);
                if (w != null) {
                    f[1] = w.getName();
                    addWorkspace(w.getName(), w.getNode());
                }
            }
        }
        for (String[] r: roles) {
            if (r[0] == null) {
                JaCaMoOrgParameters o = project.getGroupOrg(r[1]);
                if (o != null) {
                    r[0] = o.getName();
                    addWorkspace(o.getName(),o.getNode());
                }                
            }
        }
    }
    
    public List<String[]> getFocus() {
        return focus;
    }
    
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("agent "+name);
        if (asSource != null && !asSource.getName().startsWith(name)) {
            s.append(" : "+asSource);
        }
        s.append(" {\n");
        if (getNbInstances() != 1) {
            s.append("      instances: "+getNbInstances()+"\n");
        }
        if (getHost() != null) {
            s.append("      node: "+getHost()+"\n");
        }
        if (agClass != null && agClass.getClassName().length() > 0 && !agClass.getClassName().equals(Agent.class.getName())) {
            s.append("      ag-class: "+agClass+"\n");
        }
        if (bbClass != null && bbClass.getClassName().length() > 0 && !bbClass.getClassName().equals(DefaultBeliefBase.class.getName())) {
            s.append("      ag-bb-class: "+bbClass+"\n");
        }
        
        boolean first = true;
        for (ClassParameters c: archClasses) {
            if (c.getClassName().length() > 0 && !c.getClassName().equals(AgArch.class.getName())) {
                if (first) {
                    s.append("      ag-arch: "+c+"\n");
                    first = false;
                } else {
                    s.append("               "+c+"\n");
                }
            }            
        }
                
        if (options != null && !options.isEmpty()) {
            Iterator<String> i = options.keySet().iterator();
            while (i.hasNext()) {
                String k = i.next();
                try {
                    if (k.equals("beliefs")) {
                        s.append("      beliefs: ");
                        String bgn = "";
                        for (Term t: ASSyntax.parseList("["+options.get(k)+"]")) {
                            s.append(bgn+t+"\n");
                            bgn = "               ";
                        }
                    } else if (k.equals("goals")) {
                        s.append("      goals:  ");
                        String bgn = "";
                        for (Term t: ASSyntax.parseList("["+options.get(k)+"]")) {
                            s.append(bgn+t+"\n");
                            bgn = "              ";
                        }
                    } else {
                        s.append("      "+k+": "+options.get(k)+"         // app domain argument \n");
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }            
        }

        String bgn = "\n      // join: ";
        for (String w: wks) {
            s.append(bgn+w);
            JaCaMoWorkspaceParameters jw = project.getWorkspace(w); 
            if (jw != null && jw.getNode() != null)
                s.append("@"+jw.getNode());
            bgn = ", ";
        }
        
        bgn = "\n      // focus: ";
        for (String[] f: focus) {
            s.append(bgn+f[1]+"."+f[0]);
            JaCaMoWorkspaceParameters jw = project.getWorkspace(f[1]); 
            if (jw != null && jw.getNode() != null)
                s.append("@"+jw.getNode());
            bgn = ", ";
        }

        bgn = "\n      // roles: ";
        for (String[] r: roles) {
            s.append(bgn+r[2]+" in "+r[0]+"."+r[1]);
            bgn = ", ";
        }
        return s.toString().trim() + "\n   }\n";
    }
    

}
