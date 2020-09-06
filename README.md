# jboss-store

## Updated for OCP 4.5 EAP 7.2 and MySQL 5 images, change in application flows.


TL;DR - To deploy the sample

```
# oc new-project app

# oc create -f https://raw.githubusercontent.com/jboss-openshift/application-templates/master/secrets/eap7-app-secret.json -n app

# oc new-app eap72-mysql-persistent-s2i --name=jboss-store -p SOURCE_REPOSITORY_URL=https://github.com/wohshon/jboss-store -p SOURCE_REPOSITORY_REF=monolith-springmvc -p DB_JNDI=java:jboss/datasources/storeDS -p CONTEXT_DIR=''
```





