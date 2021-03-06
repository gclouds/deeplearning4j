package org.deeplearning4j.nn.modelimport.keras;

import java.io.File;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import org.deeplearning4j.nn.modelimport.keras.exceptions.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.exceptions.UnsupportedKerasConfigurationException;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.junit.Test;

/**
 * Test import of Keras models.
 *
 */
@Slf4j
public class KerasModelImportTest {
    @Test
    public void testH5WithoutTensorflowScope() throws Exception {
        MultiLayerNetwork model = loadModel("tfscope/model.h5");
        System.out.println(model.params());
        assert (model != null);
    }

    @Test
    public void testH5WithTensorflowScope() throws Exception {
        MultiLayerNetwork model = loadModel("tfscope/model.h5.with.tensorflow.scope");
        System.out.println(model.params());
        assert (model != null);
    }

    @Test
    public void testWeightAndJsonWithoutTensorflowScope() throws Exception {
        MultiLayerNetwork model = loadModel("tfscope/model.json", "tfscope/model.weight");
        System.out.println(model.params());
        assert (model != null);
    }

    @Test
    public void testWeightAndJsonWithTensorflowScope() throws Exception {
        MultiLayerNetwork model = loadModel("tfscope/model.json.with.tensorflow.scope", "tfscope/model.weight.with.tensorflow.scope");
        System.out.println(model.params());
        assert (model != null);
    }

    private MultiLayerNetwork loadModel(String modelJsonFilename, String modelWeightFilename) {
        ClassLoader classLoader = getClass().getClassLoader();
        File modelJsonFile = new File(classLoader.getResource(modelJsonFilename).getFile());
        File modelWeightFile = new File(classLoader.getResource(modelWeightFilename).getFile());

        MultiLayerNetwork network = null;
        try {
            network = KerasModelImport.importKerasSequentialModelAndWeights(modelJsonFile.getAbsolutePath(),
                    modelWeightFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKerasConfigurationException e) {
            e.printStackTrace();
        } catch (UnsupportedKerasConfigurationException e) {
            e.printStackTrace();
        }

        return network;
    }

    private MultiLayerNetwork loadModel(String modelFilename) {
        ClassLoader classLoader = getClass().getClassLoader();
        File modelFile = new File(classLoader.getResource(modelFilename).getFile());

        MultiLayerNetwork model = null;
        try {
            model = KerasModelImport.importKerasSequentialModelAndWeights(modelFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKerasConfigurationException e) {
            e.printStackTrace();
        } catch (UnsupportedKerasConfigurationException e) {
            e.printStackTrace();
        }

        return model;
    }
}
