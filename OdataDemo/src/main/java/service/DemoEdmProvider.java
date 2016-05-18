package service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.provider.CsdlAction;
import org.apache.olingo.commons.api.edm.provider.CsdlActionImport;
import org.apache.olingo.commons.api.edm.provider.CsdlAliasInfo;
import org.apache.olingo.commons.api.edm.provider.CsdlAnnotatable;
import org.apache.olingo.commons.api.edm.provider.CsdlAnnotations;
import org.apache.olingo.commons.api.edm.provider.CsdlComplexType;
import org.apache.olingo.commons.api.edm.provider.CsdlEdmProvider;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityContainer;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityContainerInfo;
import org.apache.olingo.commons.api.edm.provider.CsdlEntitySet;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.commons.api.edm.provider.CsdlEnumType;
import org.apache.olingo.commons.api.edm.provider.CsdlFunction;
import org.apache.olingo.commons.api.edm.provider.CsdlFunctionImport;
import org.apache.olingo.commons.api.edm.provider.CsdlProperty;
import org.apache.olingo.commons.api.edm.provider.CsdlPropertyRef;
import org.apache.olingo.commons.api.edm.provider.CsdlSchema;
import org.apache.olingo.commons.api.edm.provider.CsdlSingleton;
import org.apache.olingo.commons.api.edm.provider.CsdlTerm;
import org.apache.olingo.commons.api.edm.provider.CsdlTypeDefinition;
import org.apache.olingo.commons.api.ex.ODataException;
public class DemoEdmProvider implements CsdlEdmProvider{
	// Service Namespace
	public static final String NAMESPACE = "OData.Demo";

	// EDM Container
	public static final String CONTAINER_NAME = "Container";
	public static final FullQualifiedName CONTAINER = new FullQualifiedName(NAMESPACE, CONTAINER_NAME);

	// Entity Types Names
	public static final String ET_PRODUCT_NAME = "Product";
	public static final FullQualifiedName ET_PRODUCT_FQN = new FullQualifiedName(NAMESPACE, ET_PRODUCT_NAME);

	// Entity Set Names
	public static final String ES_PRODUCTS_NAME = "Products";

	public CsdlEntityContainer getEntityContainer() {

		  // create EntitySets
		  List<CsdlEntitySet> entitySets = new ArrayList<CsdlEntitySet>();
		  entitySets.add(getEntitySet(CONTAINER, ES_PRODUCTS_NAME));

		  // create EntityContainer
		  CsdlEntityContainer entityContainer = new CsdlEntityContainer();
		  entityContainer.setName(CONTAINER_NAME);
		  entityContainer.setEntitySets(entitySets);

		  return entityContainer;
		}


	public CsdlEntityContainerInfo getEntityContainerInfo(FullQualifiedName entityContainerName) {

	    // This method is invoked when displaying the Service Document at e.g. http://localhost:8080/DemoService/DemoService.svc
	    if (entityContainerName == null || entityContainerName.equals(CONTAINER)) {
	        CsdlEntityContainerInfo entityContainerInfo = new CsdlEntityContainerInfo();
	        entityContainerInfo.setContainerName(CONTAINER);
	        return entityContainerInfo;
	    }

	    return null;
	}


	public CsdlEntitySet getEntitySet(FullQualifiedName entityContainer, String entitySetName) {

		  if(entityContainer.equals(CONTAINER)){
		    if(entitySetName.equals(ES_PRODUCTS_NAME)){
		      CsdlEntitySet entitySet = new CsdlEntitySet();
		      entitySet.setName(ES_PRODUCTS_NAME);
		      entitySet.setType(ET_PRODUCT_FQN);

		      return entitySet;
		    }
		  }

		  return null;
		}


	public CsdlEntityType getEntityType(FullQualifiedName entityTypeName) {

		  // this method is called for one of the EntityTypes that are configured in the Schema
		  if(entityTypeName.equals(ET_PRODUCT_FQN)){

		    //create EntityType properties
		    CsdlProperty id = new CsdlProperty().setName("ID").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
		    CsdlProperty name = new CsdlProperty().setName("Name").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
		    CsdlProperty  description = new CsdlProperty().setName("Description").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());

		    // create CsdlPropertyRef for Key element
		    CsdlPropertyRef propertyRef = new CsdlPropertyRef();
		    propertyRef.setName("ID");

		    // configure EntityType
		    CsdlEntityType entityType = new CsdlEntityType();
		    entityType.setName(ET_PRODUCT_NAME);
		    entityType.setProperties(Arrays.asList(id, name , description));
		    entityType.setKey(Collections.singletonList(propertyRef));

		    return entityType;
		  }

		  return null;
		}


	public List<CsdlSchema> getSchemas() {

		  // create Schema
		  CsdlSchema schema = new CsdlSchema();
		  schema.setNamespace(NAMESPACE);

		  // add EntityTypes
		  List<CsdlEntityType> entityTypes = new ArrayList<CsdlEntityType>();
		  entityTypes.add(getEntityType(ET_PRODUCT_FQN));
		  schema.setEntityTypes(entityTypes);

		  // add EntityContainer
		  schema.setEntityContainer(getEntityContainer());

		  // finally
		  List<CsdlSchema> schemas = new ArrayList<CsdlSchema>();
		  schemas.add(schema);

		  return schemas;
		}


	public CsdlEnumType getEnumType(FullQualifiedName enumTypeName) throws ODataException {
		// TODO Auto-generated method stub
		return null;
	}


	public CsdlTypeDefinition getTypeDefinition(FullQualifiedName typeDefinitionName) throws ODataException {
		// TODO Auto-generated method stub
		return null;
	}


	public CsdlComplexType getComplexType(FullQualifiedName complexTypeName) throws ODataException {
		// TODO Auto-generated method stub
		return null;
	}


	public List<CsdlAction> getActions(FullQualifiedName actionName) throws ODataException {
		// TODO Auto-generated method stub
		return null;
	}


	public List<CsdlFunction> getFunctions(FullQualifiedName functionName) throws ODataException {
		// TODO Auto-generated method stub
		return null;
	}


	public CsdlTerm getTerm(FullQualifiedName termName) throws ODataException {
		// TODO Auto-generated method stub
		return null;
	}


	public CsdlSingleton getSingleton(FullQualifiedName entityContainer, String singletonName) throws ODataException {
		// TODO Auto-generated method stub
		return null;
	}


	public CsdlActionImport getActionImport(FullQualifiedName entityContainer, String actionImportName)
			throws ODataException {
		// TODO Auto-generated method stub
		return null;
	}


	public CsdlFunctionImport getFunctionImport(FullQualifiedName entityContainer, String functionImportName)
			throws ODataException {
		// TODO Auto-generated method stub
		return null;
	}


	public List<CsdlAliasInfo> getAliasInfos() throws ODataException {
		// TODO Auto-generated method stub
		return null;
	}


	public CsdlAnnotations getAnnotationsGroup(FullQualifiedName targetName) throws ODataException {
		// TODO Auto-generated method stub
		return null;
	}


	public CsdlAnnotatable getAnnotatable(FullQualifiedName annotatedName) throws ODataException {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
