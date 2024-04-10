/*
 * Copyright 2023 Marc Liberatore.
 */
package hashmaps;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import hashtables.ChainingHashTable;



/**
 * An implementation of a SimpleMap, built using the ChainingHashTable and 
 * SimpleMapEntry classes. This class should behave similarly to the built-in
 * java.util.HashMap, though it is much simpler!
 */
public class SimpleHashMap<K, V> implements SimpleMap<K, V> {
    private ChainingHashTable table;


    public SimpleHashMap() {
        this.table = new ChainingHashTable<SimpleMapEntry<K,V>>();
    }

    @Override
    public int size() {
        return this.table.size();
    }

    @Override
    public void put(K k, V v) {
        if(k==null){
            return;
        }
        SimpleMapEntry e = new SimpleMapEntry(k,v);
        SimpleMapEntry x = new SimpleMapEntry(k,null);
        SimpleMapEntry m =(SimpleMapEntry) this.table.get(x);
        
        boolean y = this.table.add(e);
        if(y == false){
            this.table.remove(m);
            this.table.add(e);
            return;
        }else{
            return;
        }
        
        
            
    }

    @Override
    public V get(K k) {
        if(k==null){
            return null;
        }
        SimpleMapEntry x = new SimpleMapEntry<K,V>(k, null);
        SimpleMapEntry m = (SimpleMapEntry) this.table.get(x);
        if(m==null){
            return null;
        }else{return (V) m.v;}
        
            
    }
 
    @Override
    public V getOrDefault(K k, V defaultValue) {
        if(k==null){
            return null;
        }
        SimpleMapEntry x = new SimpleMapEntry<K,V>(k,defaultValue);
        SimpleMapEntry m =(SimpleMapEntry) this.table.get(x);
        if(m==null){
            return defaultValue;
        }
        if(m.v!=defaultValue){
            return (V) m.v;
        }else{return defaultValue;}
        
    }

    @Override
    public V remove(K k) {
        if(k==null){
            return null;
        }
        SimpleMapEntry x = new SimpleMapEntry<K,V>(k, null);
        SimpleMapEntry m =(SimpleMapEntry) this.table.get(x);
        if(m!=null){
            this.table.remove(m);
            return (V) m.v;
        }else{return null;}
        
    }

    @Override
    public Set<K> keys() {
        Set<K> s = new HashSet<K>();
        for(ArrayList<SimpleMapEntry<K,V>> A:this.table.getTable()){
            if(A==null){
                continue;
            }else{
            for (SimpleMapEntry E: A){
                K k = (K) E.k;
                s.add(k);
            }
            }
        }
        return s;      
    }
}

  


