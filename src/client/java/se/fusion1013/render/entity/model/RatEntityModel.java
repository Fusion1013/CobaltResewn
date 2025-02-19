package se.fusion1013.render.entity.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import se.fusion1013.entity.RatEntity;

// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class RatEntityModel extends EntityModel<RatEntity> {
	private final ModelPart body1;
	private final ModelPart body2;
	private final ModelPart body3;
	private final ModelPart body4;
	private final ModelPart body5;
	private final ModelPart body6;
	private final ModelPart body7;
	private final ModelPart wing1;
	private final ModelPart wing2;
	private final ModelPart wing3;

	private final ModelPart[] body;
	private final ModelPart[] wings;

	public RatEntityModel(ModelPart root) {
		this.body1 = root.getChild("body1");
		this.body2 = root.getChild("body2");
		this.body3 = root.getChild("body3");
		this.body4 = root.getChild("body4");
		this.body5 = root.getChild("body5");
		this.body6 = root.getChild("body6");
		this.body7 = root.getChild("body7");
		this.body = new ModelPart[] {
				body1, body2, body3, body4, body5, body6, body7
		};

		this.wing1 = root.getChild("wing1");
		this.wing2 = root.getChild("wing2");
		this.wing3 = root.getChild("wing3");
		this.wings = new ModelPart[] {
				wing1, wing2, wing3
		};

	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData body1 = modelPartData.addChild("body1", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 22.0F, -3.5F));

		ModelPartData body2 = modelPartData.addChild("body2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 21.0F, -1.5F));

		ModelPartData body3 = modelPartData.addChild("body3", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 20.0F, 1.0F));

		ModelPartData body_s = body3.addChild("body_s", ModelPartBuilder.create().uv(0, 6).cuboid(-2.5F, -2.0F, -5.0F, 5.0F, 5.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData head = body_s.addChild("head", ModelPartBuilder.create().uv(14, 0).cuboid(-1.5F, -0.5F, -5.0F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-2.0F, -1.5F, -3.0F, 4.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.0F, -5.0F, 0.1309F, 0.0F, 0.0F));

		ModelPartData left_ear = head.addChild("left_ear", ModelPartBuilder.create(), ModelTransform.of(1.5F, -1.5F, -0.5F, 0.0F, -0.7418F, 0.0436F));

		ModelPartData body3_sub_2 = left_ear.addChild("body3_sub_2", ModelPartBuilder.create().uv(24, 0).mirrored().cuboid(0.0F, -1.25F, -0.25F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData left_ear2 = head.addChild("left_ear2", ModelPartBuilder.create().uv(24, 0).cuboid(-2.0F, -1.25F, -0.25F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-1.5F, -1.5F, -0.5F, 0.0F, 0.7418F, -0.0436F));

		ModelPartData tail = body_s.addChild("tail", ModelPartBuilder.create().uv(0, 19).cuboid(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.0F, 3.0F, -0.1309F, 0.0F, 0.0F));

		ModelPartData fr_leg = body_s.addChild("fr_leg", ModelPartBuilder.create().uv(0, 27).cuboid(-0.5F, 0.0F, -1.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.75F, 2.0F, -3.0F));

		ModelPartData br_leg = body_s.addChild("br_leg", ModelPartBuilder.create().uv(0, 27).cuboid(-0.5F, 0.0F, -1.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.75F, 2.0F, 1.5F));

		ModelPartData fl_leg = body_s.addChild("fl_leg", ModelPartBuilder.create(), ModelTransform.pivot(1.75F, 2.0F, -3.0F));

		ModelPartData body3_sub_8 = fl_leg.addChild("body3_sub_8", ModelPartBuilder.create().uv(0, 27).mirrored().cuboid(-0.5F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 1.0F, 0.0F));

		ModelPartData bl_leg = body_s.addChild("bl_leg", ModelPartBuilder.create(), ModelTransform.pivot(1.75F, 2.0F, 1.75F));

		ModelPartData body3_sub_10 = bl_leg.addChild("body3_sub_10", ModelPartBuilder.create().uv(0, 27).mirrored().cuboid(-0.5F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 1.0F, 0.0F));

		ModelPartData body4 = modelPartData.addChild("body4", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 21.0F, 4.0F));

		ModelPartData body5 = modelPartData.addChild("body5", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 22.0F, 7.0F));

		ModelPartData body6 = modelPartData.addChild("body6", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 23.0F, 9.5F));

		ModelPartData body7 = modelPartData.addChild("body7", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 23.0F, 11.5F));

		ModelPartData wing1 = modelPartData.addChild("wing1", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 16.0F, 1.0F));

		ModelPartData wing2 = modelPartData.addChild("wing2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 20.0F, 7.0F));

		ModelPartData wing3 = modelPartData.addChild("wing3", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 19.0F, -1.5F));
		return TexturedModelData.of(modelData, 32, 32);
	}

	@Override
	public void setAngles(RatEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		for (int i = 0; i < this.body.length; ++i) {
			this.body[i].yaw = MathHelper.cos(animationProgress * 0.9f + (float)i * 0.15f * (float)Math.PI) * (float)Math.PI * 0.05f * (float)(1 + Math.abs(i - 2));
			this.body[i].pivotX = MathHelper.sin(animationProgress * 0.9f + (float)i * 0.15f * (float)Math.PI) * (float)Math.PI * 0.2f * (float)Math.abs(i - 2);
		}
		this.wings[0].yaw = this.body[2].yaw;
		this.wings[1].yaw = this.body[4].yaw;
		this.wings[1].pivotX = this.body[4].pivotX;
		this.wings[2].yaw = this.body[1].yaw;
		this.wings[2].pivotX = this.body[1].pivotX;
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		body1.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		body2.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		body3.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		body4.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		body5.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		body6.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		body7.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		wing1.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		wing2.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		wing3.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}