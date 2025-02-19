package se.fusion1013.render.entity.model;// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import se.fusion1013.entity.CorruptedSpiderEntity;

public class CorruptedSpiderEntityModel extends EntityModel<CorruptedSpiderEntity> {
	private final ModelPart head;
	private final ModelPart neck;
	private final ModelPart body;
	private final ModelPart rightHindLeg;
	private final ModelPart leftHindLeg;
	private final ModelPart rightMiddleLeg;
	private final ModelPart leftMiddleLeg;
	private final ModelPart rightMiddleFrontLeg;
	private final ModelPart leftMiddleFrontLeg;
	private final ModelPart rightFrontLeg;
	private final ModelPart leftFrontLeg;
	public CorruptedSpiderEntityModel(ModelPart root) {
		this.head = root.getChild("head");
		this.neck = root.getChild("neck");
		this.body = root.getChild("body");
		this.rightHindLeg = root.getChild("leg1");
		this.leftHindLeg = root.getChild("leg2");
		this.rightMiddleLeg = root.getChild("leg3");
		this.leftMiddleLeg = root.getChild("leg4");
		this.rightMiddleFrontLeg = root.getChild("leg5");
		this.leftMiddleFrontLeg = root.getChild("leg6");
		this.rightFrontLeg = root.getChild("leg7");
		this.leftFrontLeg = root.getChild("leg8");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(32, 4).cuboid(-4.0F, -4.0F, -8.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 15.0F, -3.0F));

        head.addChild("bone8", ModelPartBuilder.create().uv(-6, 15).cuboid(-2.0F, 0.0F, -3.0F, 4.0F, 0.0F, 6.0F, new Dilation(0.0001F)), ModelTransform.of(4.0F, 1.75F, -9.0F, -3.1416F, 0.0F, -0.2618F));
        head.addChild("bone6", ModelPartBuilder.create().uv(-6, 15).cuboid(-2.0F, 0.0F, -3.0F, 4.0F, 0.0F, 6.0F, new Dilation(0.0001F)), ModelTransform.of(-4.0F, 1.75F, -9.0F, 0.0F, 3.1416F, 0.2618F));

        modelPartData.addChild("neck", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 15.0F, 0.0F));

        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 15.0F, 9.0F));

		ModelPartData bone3 = body.addChild("bone3", ModelPartBuilder.create().uv(0, 12).cuboid(-5.0F, -7.0F, 0.0F, 10.0F, 8.0F, 12.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 3.0F, -6.0F));
        bone3.addChild("bone5", ModelPartBuilder.create().uv(47, 27).cuboid(-1.5F, -5.0F, 0.0F, 3.0F, 5.0F, 0.0F, new Dilation(0.0001F))
                .uv(47, 24).cuboid(0.0F, -5.0F, -1.25F, 0.0F, 5.0F, 3.0F, new Dilation(0.0001F)), ModelTransform.of(0.0F, -3.0F, 11.0F, -1.5708F, 0.0F, 0.7854F));
        bone3.addChild("bone7", ModelPartBuilder.create().uv(54, 19).cuboid(0.0F, -4.0F, -3.0F, 0.0F, 4.0F, 5.0F, new Dilation(0.0001F))
                .uv(54, 24).cuboid(-2.5F, -4.0F, -0.5F, 5.0F, 4.0F, 0.0F, new Dilation(0.0001F)), ModelTransform.of(2.5F, -5.0F, 11.5F, -1.5708F, 0.0F, 0.7854F));
        bone3.addChild("bone4", ModelPartBuilder.create().uv(54, 19).cuboid(0.0F, -4.0F, -3.0F, 0.0F, 4.0F, 5.0F, new Dilation(0.0001F))
                .uv(54, 24).cuboid(-2.5F, -4.0F, -0.5F, 5.0F, 4.0F, 0.0F, new Dilation(0.0001F)), ModelTransform.of(-3.5F, -2.0F, 11.5F, -1.5708F, 0.0F, 0.7854F));
        bone3.addChild("bone2", ModelPartBuilder.create().uv(48, 0).cuboid(-3.0F, 0.0F, -5.0F, 3.0F, 0.0F, 10.0F, new Dilation(0.0001F)), ModelTransform.of(4.0F, -7.0F, 6.0F, 0.0F, 0.0F, 0.2618F));
        bone3.addChild("bone", ModelPartBuilder.create().uv(48, 0).cuboid(-3.0F, 0.0F, -5.0F, 3.0F, 0.0F, 10.0F, new Dilation(0.0001F)), ModelTransform.of(-4.0F, -7.0F, 6.0F, 3.1416F, 0.0F, 2.8798F));

        modelPartData.addChild("leg1", ModelPartBuilder.create().uv(18, 0).cuboid(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 15.0F, 4.0F));
        modelPartData.addChild("leg2", ModelPartBuilder.create().uv(28, 20).cuboid(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, 15.0F, 4.0F));
        modelPartData.addChild("leg3", ModelPartBuilder.create().uv(18, 0).cuboid(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 15.0F, 1.0F));
        modelPartData.addChild("leg4", ModelPartBuilder.create().uv(28, 20).cuboid(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, 15.0F, 1.0F));
        modelPartData.addChild("leg5", ModelPartBuilder.create().uv(18, 0).cuboid(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 15.0F, -2.0F));
        modelPartData.addChild("leg6", ModelPartBuilder.create().uv(28, 20).cuboid(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, 15.0F, -2.0F));
        modelPartData.addChild("leg7", ModelPartBuilder.create().uv(18, 0).cuboid(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 15.0F, -5.0F));
        modelPartData.addChild("leg8", ModelPartBuilder.create().uv(28, 20).cuboid(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, 15.0F, -5.0F));
        return TexturedModelData.of(modelData, 64, 32);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		neck.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		renderLegs(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	private void renderLegs(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		rightHindLeg.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		leftHindLeg.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		rightMiddleLeg.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		leftMiddleLeg.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		rightMiddleFrontLeg.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		leftMiddleFrontLeg.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		rightFrontLeg.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		leftFrontLeg.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public void setAngles(CorruptedSpiderEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		this.head.yaw = headYaw * ((float)Math.PI / 180);
		this.head.pitch = headPitch * ((float)Math.PI / 180);
        this.rightHindLeg.roll = -0.7853982f;
		this.leftHindLeg.roll = 0.7853982f;
		this.rightMiddleLeg.roll = -0.58119464f;
		this.leftMiddleLeg.roll = 0.58119464f;
		this.rightMiddleFrontLeg.roll = -0.58119464f;
		this.leftMiddleFrontLeg.roll = 0.58119464f;
		this.rightFrontLeg.roll = -0.7853982f;
		this.leftFrontLeg.roll = 0.7853982f;
        this.rightHindLeg.yaw = 0.7853982f;
		this.leftHindLeg.yaw = -0.7853982f;
		this.rightMiddleLeg.yaw = 0.3926991f;
		this.leftMiddleLeg.yaw = -0.3926991f;
		this.rightMiddleFrontLeg.yaw = -0.3926991f;
		this.leftMiddleFrontLeg.yaw = 0.3926991f;
		this.rightFrontLeg.yaw = -0.7853982f;
		this.leftFrontLeg.yaw = 0.7853982f;
		float i = -(MathHelper.cos(limbAngle * 0.6662f * 2.0f + 0.0f) * 0.4f) * limbDistance;
		float j = -(MathHelper.cos(limbAngle * 0.6662f * 2.0f + (float)Math.PI) * 0.4f) * limbDistance;
		float k = -(MathHelper.cos(limbAngle * 0.6662f * 2.0f + 1.5707964f) * 0.4f) * limbDistance;
		float l = -(MathHelper.cos(limbAngle * 0.6662f * 2.0f + 4.712389f) * 0.4f) * limbDistance;
		float m = Math.abs(MathHelper.sin(limbAngle * 0.6662f + 0.0f) * 0.4f) * limbDistance;
		float n = Math.abs(MathHelper.sin(limbAngle * 0.6662f + (float)Math.PI) * 0.4f) * limbDistance;
		float o = Math.abs(MathHelper.sin(limbAngle * 0.6662f + 1.5707964f) * 0.4f) * limbDistance;
		float p = Math.abs(MathHelper.sin(limbAngle * 0.6662f + 4.712389f) * 0.4f) * limbDistance;
		this.rightHindLeg.yaw += i;
		this.leftHindLeg.yaw += -i;
		this.rightMiddleLeg.yaw += j;
		this.leftMiddleLeg.yaw += -j;
		this.rightMiddleFrontLeg.yaw += k;
		this.leftMiddleFrontLeg.yaw += -k;
		this.rightFrontLeg.yaw += l;
		this.leftFrontLeg.yaw += -l;
		this.rightHindLeg.roll += m;
		this.leftHindLeg.roll += -m;
		this.rightMiddleLeg.roll += n;
		this.leftMiddleLeg.roll += -n;
		this.rightMiddleFrontLeg.roll += o;
		this.leftMiddleFrontLeg.roll += -o;
		this.rightFrontLeg.roll += p;
		this.leftFrontLeg.roll += -p;
	}
}