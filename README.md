# jboss-store
## EAP 6 and MySQL demo deployable on OpenShift v3 via S2i build

A shopping cart demo that showcase EAP 6 and MySQL images on OpenShift v3.  
Shopping cart object is stored in http session  
Upon checkout, orders will be persisted in MySQL database and can be viewed by the user in session   
Database schema is generated by JPA  


#### Creating the Application

##### Create the application via the Web Console using S2i  

- Select the project you have just created
- Click 'Create' at the top right hand side
- On the next page, click on Browse all templates at the left bottom
- Click on eap6-mysql-sti (or whatever name given to the eap and mysql template you have created)
- Click "Select template" on the pop up to proceed
- On the next page, click on Edit Parameter:
- The important parameters to take note of are as follows:
  - **GIT_URI** : https://github.com/wohshon/jboss-store
  - **GIT_REF** : master
  - **GIT_CONTEXT_DIR**: &lt;leave it blank&gt;
  - **DB_JNDI**: java:jboss/datasources/storeDS  
  The rest of the paramters can be arbitrary or auto generated
 
- Click on Create at the bottom of the page and wait for the build to complete
 
 Once the build is completed, do wait for 1-2 minutes as the EAP pod is still in the midst of starting up.  
 You can view the startup logs in the EAP pod by
```
oc logs -f <eap pod name>
```
Once the eap instance is up, access the deom at 
http://&lt;app-name&gt;.&lt;your domain&gt;


 




