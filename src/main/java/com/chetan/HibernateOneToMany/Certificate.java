package com.chetan.HibernateOneToMany;

/**
 * Created by chetan on 1/1/17.
 */
public class Certificate {
   private int id;
   private String name;

   public Certificate() {}
   public Certificate(String name) {
      this.name = name;
   }
   public int getId() {
      return id;
   }
   public void setId( int id ) {
      this.id = id;
   }
   public String getName() {
      return name;
   }
   public void setName( String name ) {
      this.name = name;
   }
   //so if argument in equals is null returns false
    //if class is diffrent return false
    //iff id and name is equal return true else false
   public boolean equals(Object obj) {
      if (obj == null) return false;
      if (!this.getClass().equals(obj.getClass())) return false;

      Certificate obj2 = (Certificate)obj;

      if((this.id == obj2.getId()) && (this.name.equals(obj2.getName())))
      {
         return true;
      }
      return false;
   }
   //id+name is taken as hashcode
   public int hashCode() {
      int tmp = 0;
      tmp = ( id + name ).hashCode();
      return tmp;
   }
}
